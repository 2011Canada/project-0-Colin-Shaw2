package com.revature.models;



import com.revature.enums.AccountState;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.menus.Displayable;

public class Account implements Displayable{

	int accountID;//this is not unique for every account just for every user
	String accountOwner;
//	Date openDate;
	long balance;
	AccountState accountState = AccountState.PENDING;

	public Account() {
//		this.openDate = new Date();
		this.balance = 0;
		this.accountID = 0;
		this.accountOwner = "default";
	}
	
	
	public Account(long balance, int acountID, String accountOwner) {
//		this.openDate = new Date();
		this.balance = balance;
		this.accountID = acountID;
		this.accountOwner = accountOwner;
	}

	public Account(long balance) {
//		this.openDate = openDate;
		this.balance = balance;
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
		return balance;
	}
	
	public void setBalance(long balance) throws NegativeBalanceException{
		if(balance < 0) {
			throw new NegativeBalanceException();
		}
		this.balance = balance;
	}
	
	
	public void approveAccount() throws UnexpectedAccountStateException{
		if(this.accountState != AccountState.PENDING) {
			throw new UnexpectedAccountStateException(AccountState.PENDING, this.accountState);
		}
		this.accountState= AccountState.APPROVED;
	}
	
	public void declineAccount() throws UnexpectedAccountStateException{
		if(this.accountState != AccountState.PENDING) {
			throw new UnexpectedAccountStateException(AccountState.PENDING, this.accountState);
		}
		this.accountState= AccountState.DENIED;
	}
	
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountOwner=" + accountOwner + 
				", Balance=" + balance + "]";
	}

	@Override
	public String display() {
		//TODO
		return toString();
	}
}
