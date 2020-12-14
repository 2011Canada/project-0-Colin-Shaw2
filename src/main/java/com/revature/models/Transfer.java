package com.revature.models;

import java.util.Date;

import com.revature.enums.TransferState;
import com.revature.exceptions.UnexpectedTransferStateException;

public class Transfer {
	Date dateMade;
	int ammount;
	int transferId;
	Customer sendingCustomer;
	Customer receivingCustomer;
	TransferState transferState;
	
	public Transfer(int ammount, Customer sendingCustomer, Customer receivingCustomer) {
		this.dateMade = new Date();
		this.ammount = ammount;
		this.transferId = 0;
		this.sendingCustomer = sendingCustomer;
		this.receivingCustomer = receivingCustomer;
		this.transferState = TransferState.PENDING;
	}
	
	public Transfer(Date dateMade, int ammount, Customer sendingCustomer, Customer receivingCustomer) {
		this.dateMade = dateMade;
		this.ammount = ammount;
		this.transferId = 0;
		this.sendingCustomer = sendingCustomer;
		this.receivingCustomer = receivingCustomer;
		this.transferState = TransferState.PENDING;
	}

	public int getTransferId() {
		return transferId;
	}

	public TransferState getTransferState() {
		return transferState;
	}
	
	public void approveTransfer() throws UnexpectedTransferStateException{
		if(transferState.equals(TransferState.PENDING)) {
			transferState = TransferState.APPROVED;
		}else {
			throw new UnexpectedTransferStateException(TransferState.PENDING, this.transferState);
		}
	}
	public void declineTransfer() throws UnexpectedTransferStateException{
		if(transferState.equals(TransferState.PENDING)) {
			transferState = TransferState.DENIED;
		}else {
			throw new UnexpectedTransferStateException(TransferState.PENDING, this.transferState);
		}
	}

	@Override
	public String toString() {
		return "Transfer [dateMade=" + dateMade + ", ammount=" + ammount + ", transferId=" + transferId
				+ ", sendingCustomer=" + sendingCustomer + ", receivingCustomer=" + receivingCustomer
				+ ", transferState=" + transferState.toString() + "]";
	}


}
