package com.revature.models;



import com.revature.enums.AccountState;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UnexpectedAccountStateException;

public class Account{

	int accountID;//this is not unique for every account just for every user
	String accountOwner;
	long balance;
	AccountState accountState = AccountState.PENDING;

	public Account() {
		this.balance = 0;
		this.accountID = 0;
		this.accountOwner = "default";
	}
	
	
	public Account(long balance, int acountID, String accountOwner) {
		this.balance = balance;
		this.accountID = acountID;
		this.accountOwner = accountOwner;
	}
	public Account(long balance, int acountID, String accountOwner, AccountState accountState) {
		this.balance = balance;
		this.accountID = acountID;
		this.accountOwner = accountOwner;
		this.accountState = accountState;
	}

	public Account(long balance) {
		this.balance = balance;
		this.accountID = 0;
	}

	
	public int getAccountID() {
		return accountID;
	}
	
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
		return "Account owner is " + accountOwner + " accountID is " + accountID + 
				" current balance is " + balance + "account state is " + accountState;
	}


	public AccountState getAccountState() {
		return accountState;
	}


	public void setAccountId(int i) {
		this.accountID = i;
	}

}
