package com.revature.exceptions;

import com.revature.enums.TransferState;

public class UnexpectedTransferStateException extends Exception {
	
	public UnexpectedTransferStateException() {
		super("Invalid Transfer State");
	}
	public UnexpectedTransferStateException(TransferState expected, TransferState got) {
		super("Invalid Transfer State\nExcepted " + expected 
				+ " Got " + got);
	}

}