package com.revature.exceptions;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException() {
		super("Account Not Found");
	}
}
