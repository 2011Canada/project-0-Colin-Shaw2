package com.revature.exceptions;

import com.revature.enums.AccountState;

public class UnexpectedAccountStateException extends Exception {
	
	public UnexpectedAccountStateException() {
		super("Invalid Account State");
	}
	public UnexpectedAccountStateException(AccountState expected, AccountState got) {
		super("Invalid Account State\nExcepted " + expected 
				+ " Got " + got);
	}

}
