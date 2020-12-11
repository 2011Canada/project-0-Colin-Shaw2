package com.revature.exceptions;

public class NegativeBalanceException extends Exception {
	
	public NegativeBalanceException() {
		super("Cannot have a negative balance");
	}
}
