package com.revature.exceptions;

public class InvalidArgumentLengthException extends Exception {

	public InvalidArgumentLengthException() {
		super("Wrong Number of Arguments Passed");
	}
	
	public InvalidArgumentLengthException(int expected, int got) {
		super("Wrong Number of Arguments Passed\nExcepted " + expected 
				+ " Got " + got);
	}
	
}
