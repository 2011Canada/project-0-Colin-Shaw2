package com.revature.models;

import java.util.Date;

public class Account {
	
	int acountID;
	Date openDate;
	long Balance;

	public Account() {
		this.openDate = new Date();
		this.Balance = 0;
	}
	
	public Account(long balance) {
		this.openDate = new Date();
		this.Balance = balance;
	}
	
	public Account(Date openDate, long balance, int acountID) {
		this.openDate = openDate;
		this.Balance = balance;
		this.acountID = acountID;
	}

}
