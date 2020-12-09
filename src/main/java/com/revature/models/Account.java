package com.revature.models;

import java.util.Date;

public class Account {

	int accountID;
	String accountOwner;
	Date openDate;
	long Balance;

	public Account() {
		this.openDate = new Date();
		this.Balance = 0;
		this.accountID = 0;
	}
	
	public Account(long balance) {
		this.openDate = new Date();
		this.Balance = balance;
		this.accountID = 0;
	}

	public Account(long balance, int acountID, String accountOwner) {
		this.openDate = new Date();
		this.Balance = balance;
		this.accountID = acountID;
		this.accountOwner = accountOwner;
	}

	public Account(Date openDate, long balance, int acountID) {
		this.openDate = openDate;
		this.Balance = balance;
		this.accountID = acountID;
	}

	
	public int getAccountID() {
		return accountID;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public long getBalance() {
		return Balance;
	}
	
	public void setBalance(long balance) {
		Balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountOwner=" + accountOwner + ", openDate=" + openDate
				+ ", Balance=" + Balance + "]";
	}
}
