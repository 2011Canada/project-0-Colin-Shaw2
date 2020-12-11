package com.revature.models;



import com.revature.enums.AccountState;
import com.revature.menus.Displayable;

public class Account implements Displayable{

	int accountID;//this is not unique for every account just for every user
	String accountOwner;
//	Date openDate;
	long Balance;
	AccountState accountState = AccountState.PENDING;

	public Account() {
//		this.openDate = new Date();
		this.Balance = 0;
		this.accountID = 0;
		this.accountOwner = "default";
	}
	
	
	public Account(long balance, int acountID, String accountOwner) {
//		this.openDate = new Date();
		this.Balance = balance;
		this.accountID = acountID;
		this.accountOwner = accountOwner;
	}

	public Account(long balance) {
//		this.openDate = openDate;
		this.Balance = balance;
		this.accountID = 0;
	}

	
	public int getAccountID() {
		return accountID;
	}
	
//	public Date getOpenDate() {
//		return openDate;
//	}
//	
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
		return "Account [accountID=" + accountID + ", accountOwner=" + accountOwner + 
				", Balance=" + Balance + "]";
	}

	@Override
	public String display() {
		//TODO
		return toString();
	}
}
