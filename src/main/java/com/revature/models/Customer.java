package com.revature.models;

import java.util.ArrayList;

public class Customer extends User {
	
	ArrayList<Account> accounts;
	
	
	public Customer(int id, String username, String password) {
		super(id, username, password);
		accounts.add(new Account());
	}
	
	
	public void withdraw(long amount) {
	}
	
	public void deposit(long amount) {
	}
	
	public void transfer(long amount, Customer customer) {
		
	}
	
	public void acceptTransfer() {
		
	}






}
