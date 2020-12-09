package com.revature.models;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {
	
	ArrayList<Account> accounts;
	
	
	public Customer(String username, String password) {
		super(0, username, password);
		accounts = new ArrayList<>();
		accounts.add(new Account(new Date(), 0, 16));
	}
	
	public Customer(int id, String username, String password) {
		super(id, username, password);
		accounts = new ArrayList<>();
		accounts.add(new Account(new Date(), 0, 16));
	}
	
	
	public boolean addAccount(Account a) {
		return accounts.add(a);
	}

	//TODO all these methods should be deleted and moved to SQL
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public Account getAccountByID(int id) {
		for(Account a : accounts) {
			if(a.getAccountID() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Account updateAccountByID(int id, Account newAccount) {
		for(Account a : accounts) {
			if(a.getAccountID() == id) {
				a = newAccount;
				return newAccount;
			}
		}
		return null;
	}






}
