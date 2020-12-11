package com.revature.models;

import java.util.ArrayList;

public class Customer extends User {
	
	ArrayList<Account> accounts;
	
	
	public Customer(String username, String password) {
		super(username, password);
		accounts = new ArrayList<>();
	}
		
	
	public Customer(String username, String password, ArrayList<Account> accounts) {
		super(username, password);
		this.accounts = accounts;
	}

	public boolean addAccount(Account a) {
		return accounts.add(a);
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	

}
