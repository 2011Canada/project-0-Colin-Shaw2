package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.enums.AccountState;
import com.revature.enums.TransferState;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.TransferDAO;
import com.revature.repositories.file.AccountFileDAO;
import com.revature.repositories.file.CustomerFileDAO;
import com.revature.repositories.file.TransferFileDAO;

public class CustomerServiceController implements CustomerServiceInterface {

	private static AccountDAO accountDAO;
	private static CustomerDAO customerDAO;
	private static TransferDAO transferDAO;
	private static Logger transactionLogger = LogManager.getLogger("com.revature.project0ColinTransactionLogger");
	private static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");
	
	public CustomerServiceController(AccountDAO accDAO, CustomerDAO custDAO, TransferDAO transDAO) {
		accountDAO = accDAO;
		customerDAO = custDAO;
		transferDAO = transDAO;
	}
	
	@Override
	public Account applyForBankAccount(Customer currentCustomer, long initialBalance) throws UserNotFoundException, SQLException, NegativeBalanceException, AccountNotFoundException {
		eventLogger.info("applyForBankAccount "  + currentCustomer + " " + initialBalance);
		if(initialBalance < 0) {throw new NegativeBalanceException();}
		Account a = new Account(initialBalance, currentCustomer.getUsername());
		a = accountDAO.addAccount(currentCustomer, a);
		currentCustomer.addAccount(a);
		return a;
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
	public Account withdraw(Customer currentCustomer, int accountID, int amount)  throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException, UnexpectedAccountStateException{
		eventLogger.info("withdraw "  + currentCustomer + " " + accountID + " " + amount);
		if(amount <= 0 ) {throw new NegativeBalanceException();}
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		if(acc.getAccountState()!=AccountState.APPROVED) {
			throw new UnexpectedAccountStateException();
		}
		acc.setBalance(acc.getBalance() - amount);
		transactionLogger.info(currentCustomer.getUsername() + " Withdrew "+ amount + " from account with ID "+ accountID);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	public Account deposit(Customer currentCustomer, int accountID, int amount)  throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException, UnexpectedAccountStateException{
		eventLogger.info("deposit "  + currentCustomer + " " + accountID + " " + amount);
		if(amount <= 0 ) {throw new NegativeBalanceException();}
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		if(acc.getAccountState()!=AccountState.APPROVED) {
			throw new UnexpectedAccountStateException();
		}
		acc.setBalance(acc.getBalance() + amount);
		transactionLogger.info(currentCustomer.getUsername() + " Deposited "+ amount + " from account with ID "+ accountID);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	public List<Account> internalAccountTransfer(Customer currentCustomer, int fromAccountID, 
			int toAccountID, int amount)  throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException, UnexpectedAccountStateException {
		eventLogger.info("internalAccountTransfer "  + currentCustomer + " " + fromAccountID
				+ " " + toAccountID + " " + amount);
		if(amount <= 0 ) {throw new NegativeBalanceException();}
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
		Transfer t = new Transfer(amount, currentCustomer, fromAccountID, toCustomer, toAccountID, 100, TransferState.PENDING);
		return transferDAO.addTransfer(t);
	}

	@Override
	public Transfer acceptTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException, SQLException, UserNotFoundException, NegativeBalanceException, UnexpectedAccountStateException {
		eventLogger.info("acceptTransfer "  + currentCustomer + " " + transferID);
		Transfer t = transferDAO.findTransferByID(transferID);
		if(!t.getReceivingCustomer().getUsername().equals(currentCustomer.getUsername())) {
			throw new UnexpectedTransferStateException();
		}
		withdraw(t.getSendingCustomer(), t.getSendingAccountId(), t.getAmmount());
		deposit(t.getReceivingCustomer(), t.getReceivingAccountId(), t.getAmmount());
		t.approveTransfer();
		return transferDAO.updateTransferByID(t, transferID);
	}

	@Override
	public Transfer declineTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException, SQLException, UserNotFoundException {
		eventLogger.info("declineTransfer "  + currentCustomer + " " + transferID);
		Transfer t = transferDAO.findTransferByID(transferID);
		if(!t.getReceivingCustomer().getUsername().equals(currentCustomer.getUsername())) {
			throw new UnexpectedTransferStateException();
		}
		t.declineTransfer();
		return transferDAO.updateTransferByID(t, transferID);
	}
	
	
	@Override
	public ArrayList<Transfer> viewPendingTransfers(Customer currentCustomer) throws AccountNotFoundException, SQLException, UserNotFoundException, TransferNotFoundException {
		eventLogger.info("viewPendingTransfers "  + currentCustomer );
		return new ArrayList<>(transferDAO.findAllPendingTransfersForCustomer(currentCustomer.getUsername()));
	}

}
