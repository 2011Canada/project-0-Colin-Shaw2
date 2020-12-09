package com.revature.repositories;

import java.util.HashMap;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public class AccountFileDAO implements AccountDAO {

	static HashMap<String, User> db = FakeFileDB.db;
	
	@Override
	public Customer addAccount(Customer u, Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateAccount(Customer u, Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account[] findAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
