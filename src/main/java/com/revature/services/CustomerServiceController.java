package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountFileDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerFileDAO;
import com.revature.repositories.TransferDAO;
import com.revature.repositories.TransferFileDAO;

public class CustomerServiceController implements CustomerServiceInterface {

	private static AccountDAO accountDAO = new AccountFileDAO();
	private static CustomerDAO customerDAO = new CustomerFileDAO();
	private static TransferDAO transferDAO = new TransferFileDAO();
	private static Logger transactionLogger = LogManager.getLogger("com.revature.project0ColinTransactionLogger");
	private static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");
	
	@Override
	public Customer applyForBankAccount(Customer currentCustomer, long initialBalance) throws UserNotFoundException, SQLException {
		eventLogger.info("applyForBankAccount "  + currentCustomer + " " + initialBalance);
		currentCustomer.addAccount(new Account(initialBalance));
		return customerDAO.updateCustomer(currentCustomer);
	}
	
	@Override
	public List<Account> viewAccounts(Customer currentCustomer) throws UserNotFoundException, AccountNotFoundException, SQLException{
		eventLogger.info("viewAccounts "  + currentCustomer );
		return customerDAO.findCustomerByName(currentCustomer.getUsername()).getAccounts();
	}
	
	@Override
	public long viewBalance(Customer currentCustomer, int accountID) throws AccountNotFoundException, SQLException {
		eventLogger.info("viewBalance "  + currentCustomer + " " + accountID);
		return accountDAO.findAccountByCustomerandID(currentCustomer, accountID).getBalance();
	}

	@Override
	public Account withdraw(Customer currentCustomer, int accountID, int amount)  throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException{
		eventLogger.info("withdraw "  + currentCustomer + " " + accountID + " " + amount);
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		acc.setBalance(acc.getBalance() - amount);
		transactionLogger.info(currentCustomer.getUsername() + " Withdrew "+ amount + " from account with ID "+ accountID);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	public Account deposit(Customer currentCustomer, int accountID, int amount)  throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException{
		eventLogger.info("deposit "  + currentCustomer + " " + accountID + " " + amount);
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		acc.setBalance(acc.getBalance() + amount);
		transactionLogger.info(currentCustomer.getUsername() + " Deposited "+ amount + " from account with ID "+ accountID);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	public List<Account> internalAccountTransfer(Customer currentCustomer, int fromAccountID, 
			int toAccountID, int amount)  throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException {
		eventLogger.info("internalAccountTransfer "  + currentCustomer + " " + fromAccountID
				+ " " + toAccountID + " " + amount);
		List<Account> accounts = new ArrayList<>();
		accounts.add(withdraw(currentCustomer, fromAccountID, amount));
		accounts.add(deposit(currentCustomer, toAccountID, amount));
		return accounts;
	}

	@Override
	public Transfer externalAccountTransfer(Customer currentCustomer, int fromAccountID, String toCustomerName,
			int toAccountID, int amount) throws UserNotFoundException, AccountNotFoundException, SQLException, TransferNotFoundException {
		eventLogger.info("externalAccountTransfer "  + currentCustomer + " " + fromAccountID
				+ " " + toAccountID + " " + amount);
		Customer toCustomer = customerDAO.findCustomerByName(toCustomerName);
		//TODO handle transferID
		Transfer t = new Transfer(amount, currentCustomer, fromAccountID, toCustomer, toAccountID, 100);
		return transferDAO.addTransfer(t);
	}

	@Override
	public Transfer acceptTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException {
		eventLogger.info("acceptTransfer "  + currentCustomer + " " + transferID);
		Transfer t = transferDAO.findTransferByID(transferID);
		t.approveTransfer();
		return transferDAO.updateTransferByID(t, transferID);
	}

	@Override
	public Transfer declineTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException {
		eventLogger.info("declineTransfer "  + currentCustomer + " " + transferID);
		Transfer t = transferDAO.findTransferByID(transferID);
		t.declineTransfer();
		return transferDAO.updateTransferByID(t, transferID);
	}
	
	
	@Override
	public ArrayList<Transfer> viewPendingTransfers(Customer currentCustomer) throws AccountNotFoundException {
		eventLogger.info("viewPendingTransfers "  + currentCustomer );
		return new ArrayList<>(transferDAO.findAllPendingTransfers());
	}

}
