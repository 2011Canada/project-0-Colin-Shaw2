package com.revature.models;

import java.util.ArrayList;

public class Customer extends User {
	
	ArrayList<Account> accounts;
	
	
	public Customer(String username, String password) {
		super(username, password);
		accounts = new ArrayList<>();
	}
		
	
	public Customer(String username, String password, ArrayList<Account> accounts) {
		super(username, password);
		this.accounts = accounts;
	}

	public boolean addAccount(Account a) {
		return accounts.add(a);
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		return true;
	}
	
	

}
