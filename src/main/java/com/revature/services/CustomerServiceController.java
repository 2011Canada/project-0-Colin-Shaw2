package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.UserFileDAO;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountFileDAO;
import com.revature.repositories.UserDAO;

public class CustomerServiceController extends UserServiceController implements CustomerServiceInterface {

	private static AccountDAO accountDAO = new AccountFileDAO();
	
	@Override
	public Customer applyForBankAccount(Customer currentCustomer, long initialBalance) {
		currentCustomer.addAccount(new Account(initialBalance));
		return userDAO.updateCustomer(currentCustomer);
	}
	
	@Override
	public List<Account> viewAccounts(Customer currentCustomer, int accountID){
		return userDAO.findCustomerByName(currentCustomer.getUsername()).getAccounts();
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
	public Boolean internalAccountTransfer(Customer currentCustomer, int fromAccountID, int toAccountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean externalAccountTransfer(Customer currentCustomer, int fromAccountID, Customer toAccount,
			int toAccountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean acceptTransfer(Customer currentCustomer, int transferID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Transfer> viewPendingTransfers(Customer currentCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

}
