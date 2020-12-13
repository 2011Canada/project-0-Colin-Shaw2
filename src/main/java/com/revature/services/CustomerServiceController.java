package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UnexpectedTransferStateException;
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
	public Customer applyForBankAccount(Customer currentCustomer, long initialBalance) {
		eventLogger.info("applyForBankAccount "  + currentCustomer + " " + initialBalance);
		currentCustomer.addAccount(new Account(initialBalance));
		return customerDAO.updateCustomer(currentCustomer);
	}
	
	@Override
	public List<Account> viewAccounts(Customer currentCustomer, int accountID){
		eventLogger.info("viewAccounts "  + currentCustomer + " " + accountID);
		return customerDAO.findCustomerByName(currentCustomer.getUsername()).getAccounts();
	}
	
	@Override
	public long viewBalance(Customer currentCustomer, int accountID) {
		eventLogger.info("viewBalance "  + currentCustomer + " " + accountID);
		return accountDAO.findAccountByCustomerandID(currentCustomer, accountID).getBalance();
	}

	@Override
	public Account withdraw(Customer currentCustomer, int accountID, int amount)  throws NegativeBalanceException{
		eventLogger.info("withdraw "  + currentCustomer + " " + accountID + " " + amount);
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		acc.setBalance(acc.getBalance() - amount);
		transactionLogger.info(currentCustomer.getUsername() + " Withdrew "+ amount + " from account with ID "+ accountID);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	public Account deposit(Customer currentCustomer, int accountID, int amount)  throws NegativeBalanceException{
		eventLogger.info("deposit "  + currentCustomer + " " + accountID + " " + amount);
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		acc.setBalance(acc.getBalance() + amount);
		transactionLogger.info(currentCustomer.getUsername() + " Depositted "+ amount + " from account with ID "+ accountID);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	//TODO return type
	public Boolean internalAccountTransfer(Customer currentCustomer, int fromAccountID, 
			int toAccountID, int amount)  throws NegativeBalanceException {
		eventLogger.info("internalAccountTransfer "  + currentCustomer + " " + fromAccountID
				+ " " + toAccountID + " " + amount);
		this.withdraw(currentCustomer, fromAccountID, amount);
		this.deposit(currentCustomer, toAccountID, amount);
		return true;
	}

	@Override
	//TODO return type
	public Boolean externalAccountTransfer(Customer currentCustomer, int fromAccountID, String toCustomerName,
			int toAccountID, int amount) {
		eventLogger.info("externalAccountTransfer "  + currentCustomer + " " + fromAccountID
				+ " " + toAccountID + " " + amount);
		Customer toCustomer = customerDAO.findCustomerByName(toCustomerName);
		Transfer t = new Transfer(amount, currentCustomer, toCustomer);
		transferDAO.addTransfer(t);
		return null;
	}

	@Override
	//TODO return type
	public Boolean acceptTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException {
		eventLogger.info("acceptTransfer "  + currentCustomer + " " + transferID);
		Transfer t = transferDAO.findTransferByID(transferID);
		t.approveTransfer();
		transferDAO.updateTransferByID(t, transferID);
		return null;
	}

	@Override
	//TODO return type
	public Boolean declineTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException {
		eventLogger.info("declineTransfer "  + currentCustomer + " " + transferID);
		Transfer t = transferDAO.findTransferByID(transferID);
		t.declineTransfer();
		transferDAO.updateTransferByID(t, transferID);
		return null;
	}
	
	
	@Override
	public ArrayList<Transfer> viewPendingTransfers(Customer currentCustomer) {
		eventLogger.info("viewPendingTransfers "  + currentCustomer );
		return new ArrayList<>(transferDAO.findAllPendingTransfers());
	}

}
