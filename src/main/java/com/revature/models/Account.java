package com.revature.models;

import com.revature.enums.AccountState;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UnexpectedAccountStateException;

public class Account {

	int accountID;// this is not unique for every account just for every user
	String accountOwner;
	long balance;
	AccountState accountState = AccountState.PENDING;

	public Account() {
		this.balance = 0;
		this.accountID = 0;
		this.accountOwner = "default";
	}
	public Account(long balance,String accountOwner) {
		this.balance = balance;
		this.accountOwner = accountOwner;
	}

	public Account(long balance, int acountID, String accountOwner, AccountState accountState) {
		this.balance = balance;
		this.accountID = acountID;
		this.accountOwner = accountOwner;
		this.accountState = accountState;
	}


	public int getAccountID() {
		return accountID;
	}

	public long getBalance() {
		return balance;
	}
	
	public String getMoney() {
		String s = Long.toString(balance);
		if(balance<10) {
			return "0.0"+s;
		}
		else if(balance<100) {
			return "0."+s;
		}
		String beforeDec = s.substring(0, s.length()-2);
		String afterDec = s.substring(s.length()-2, s.length());
		return beforeDec + "." + afterDec;
	}

	public void setBalance(long balance) throws NegativeBalanceException {
		if (balance < 0) {
			throw new NegativeBalanceException();
		}
		this.balance = balance;
	}

	public void approveAccount() throws UnexpectedAccountStateException {
		if (this.accountState != AccountState.PENDING) {
			throw new UnexpectedAccountStateException(AccountState.PENDING, this.accountState);
		}
		this.accountState = AccountState.APPROVED;
	}

	public void declineAccount() throws UnexpectedAccountStateException {
		if (this.accountState != AccountState.PENDING) {
			throw new UnexpectedAccountStateException(AccountState.PENDING, this.accountState);
		}
		this.accountState = AccountState.DENIED;
	}

	@Override
	public String toString() {
		return "Account owner is " + accountOwner + " accountID is " + accountID + " current balance is " + balance
				+ "account state is " + accountState;
	}

	public AccountState getAccountState() {
		return accountState;
	}

	public void setAccountId(int i) {
		this.accountID = i;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		result = prime * result + ((accountOwner == null) ? 0 : accountOwner.hashCode());
		result = prime * result + ((accountState == null) ? 0 : accountState.hashCode());
		result = prime * result + (int) (balance ^ (balance >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		if (accountOwner == null) {
			if (other.accountOwner != null)
				return false;
		} else if (!accountOwner.equals(other.accountOwner))
			return false;
		if (accountState != other.accountState)
			return false;
		if (balance != other.balance)
			return false;
		return true;
	}

}
