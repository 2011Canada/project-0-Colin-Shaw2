package com.revature.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountFileDAO;
import com.revature.repositories.TransferDAO;
import com.revature.repositories.TransferFileDAO;

public class CustomerServiceController extends UserServiceController implements CustomerServiceInterface {

	private static AccountDAO accountDAO = new AccountFileDAO();
	private static TransferDAO transferDAO = new TransferFileDAO();
	
	@Override
	public Customer applyForBankAccount(Customer currentCustomer, long initialBalance) {
		currentCustomer.addAccount(new Account(initialBalance));
		return customerDAO.updateCustomer(currentCustomer);
	}
	
	@Override
	public List<Account> viewAccounts(Customer currentCustomer, int accountID){
		return customerDAO.findCustomerByName(currentCustomer.getUsername()).getAccounts();
	}
	
	@Override
	public long viewBalance(Customer currentCustomer, int accountID) {
		return accountDAO.findAccountByCustomerandID(currentCustomer, accountID).getBalance();
	}

	@Override
	public Account withdraw(Customer currentCustomer, int accountID, int amount) {
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		acc.setBalance(acc.getBalance() - amount);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	public Account deposit(Customer currentCustomer, int accountID, int amount) {
		Account acc = accountDAO.findAccountByCustomerandID(currentCustomer, accountID);
		acc.setBalance(acc.getBalance() + amount);
		return accountDAO.updateAccountByCustomerandID(currentCustomer, accountID, acc);
	}

	@Override
	//TODO return type
	public Boolean internalAccountTransfer(Customer currentCustomer, int fromAccountID, 
			int toAccountID, int amount) {
		//TODO exceptions
		this.withdraw(currentCustomer, fromAccountID, amount);
		this.deposit(currentCustomer, toAccountID, amount);
		return true;
	}

	@Override
	//TODO return type
	public Boolean externalAccountTransfer(Customer currentCustomer, int fromAccountID, String toCustomerName,
			int toAccountID, int amount) {
		Customer toCustomer = customerDAO.findCustomerByName(toCustomerName);
		Transfer t = new Transfer(amount, currentCustomer, toCustomer);
		transferDAO.addTransfer(t);
		return null;
	}

	@Override
	//TODO return type
	public Boolean acceptTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException {
		Transfer t = transferDAO.findTransferByID(transferID);
		t.approveTransfer();
		transferDAO.updateTransferByID(t, transferID);
		return null;
	}

	@Override
	//TODO return type
	public Boolean declineTransfer(Customer currentCustomer, int transferID) throws UnexpectedTransferStateException {
		Transfer t = transferDAO.findTransferByID(transferID);
		t.declineTransfer();
		transferDAO.updateTransferByID(t, transferID);
		return null;
	}
	
	
	@Override
	public ArrayList<Transfer> viewPendingTransfers(Customer currentCustomer) {
		return new ArrayList<>(transferDAO.findAllPendingTransfers());
	}

}
