package com.revature.repositories.file;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.CustomerDAO;

public class AccountFileDAO implements AccountDAO {

	static HashMap<String, User> db = FakeFileDB.db;
	
	static CustomerDAO customerDAO = new CustomerFileDAO();
	
	@Override
	public Account addAccount(Customer u, Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccountByCustomerandID(Customer c, int id, Account a) {
		db.put(c.getUsername(), c);
		return a;
	}

	

	public List<Account> findAllAccountsFromCustomerName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountByCustomerandID(Customer c, int id) {
		for(Account a :c.getAccounts()) {
			if(a.getAccountID() == id) {
				return a;
			}
		}
		return null;
	}

	@Override
	public List<Account> findAllPendingAccountsFromCustomerName(String username)
			throws AccountNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
