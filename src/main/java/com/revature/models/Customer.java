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
	
	
	public void withdraw(long amount) {
	}
	
	public void deposit(long amount) {
	}
	
	public void transfer(long amount, Customer customer) {
		
	}
	
	public void acceptTransfer() {
		
	}






}
