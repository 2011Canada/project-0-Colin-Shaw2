package com.revature.repositories;

import java.util.HashMap;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public class AccountFileDAO implements AccountDAO {

	static HashMap<String, User> db = FakeFileDB.db;
	
	static CustomerDAO customerDAO = new CustomerFileDAO();
	
	@Override
	public Customer addAccount(Customer u, Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccountByCustomerandID(Customer c, int id, Account a) {
		db.put(c.getUsername(), c);
		return a;
	}

	@Override
	public Account[] findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public Account[] findAllAccountsFromCustomerName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountByCustomerandID(Customer c, int id) {
		return customerDAO.findCustomerByName(c.getUsername()).getAccountByID(id);
	}

}
