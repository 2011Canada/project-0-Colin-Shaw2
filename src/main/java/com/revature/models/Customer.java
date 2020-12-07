package com.revature.models;

public class Customer extends User {
	
	long balance;
	
	public Customer(int id, long balance) {
		super(id);
		this.balance = balance;
	}
	
	
	public long getbalance() {
		return balance;
	}
	
	public void withdraw(long amount) {
	}
	
	public void deposit(long amount) {
	}
	
	public void transfer(long amount, Customer customer) {
		
	}
	
	public void acceptTransfer() {
		
	}


	public User login() {
		// TODO Auto-generated method stub
		return null;
	}


	public User registerNewAcount() {
		// TODO Auto-generated method stub
		return null;
	}
}
