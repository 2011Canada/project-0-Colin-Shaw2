package com.revature.services;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.UserFileDAO;
import com.revature.repositories.UserDAO;

public class CustomerServiceController extends UserServiceController implements CustomerServiceInterface {

	@Override
	public Customer applyForBankAccount(Customer currentCustomer, long initialBalance) {
		currentCustomer.addAccount(new Account(initialBalance));
		return dao.updateCustomer(currentCustomer);
	}

	@Override
	public Account viewBalance(Customer currentCustomer, int accountID) {
		Customer c = dao.findCustomerByName(currentCustomer.getUsername());
		return null;
	}

	@Override
	public Account withdraw(Customer currentCustomer, int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deposit(Customer currentCustomer, int accountID) {
		// TODO Auto-generated method stub
		return null;
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
