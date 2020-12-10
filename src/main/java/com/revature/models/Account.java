package com.revature.models;

import java.util.Date;

import com.revature.enums.AccountState;

public class Account {

	int accountID;
	String accountOwner;
	Date openDate;
	long Balance;
	AccountState accountState = AccountState.PENDING;

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
	
	
	public void approveAccount() {
		if(this.accountState != AccountState.PENDING) {
			//TODO exception
		}
		this.accountState= AccountState.APPROVED;
	}
	
	public void declineAccount() {
		if(this.accountState != AccountState.PENDING) {
			//TODO exception
		}
		this.accountState= AccountState.DENIED;
	}
	
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountOwner=" + accountOwner + ", openDate=" + openDate
				+ ", Balance=" + Balance + "]";
	}
}
