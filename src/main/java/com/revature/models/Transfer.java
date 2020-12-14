package com.revature.models;

import java.util.Date;

import com.revature.enums.TransferState;
import com.revature.exceptions.UnexpectedTransferStateException;

public class Transfer {
	Date dateMade;
	int ammount;
	Customer sendingCustomer;
	int sendingAccountId;
	Customer receivingCustomer;
	int receivingAccountId;
	TransferState transferState;
	int transferId;
	
	public Transfer(int ammount, Customer sendingCustomer,int sendingAccountId, Customer receivingCustomer,
			int receivingAccountId, int transferID) {
		this.dateMade = new Date();
		this.ammount = ammount;
		this.sendingCustomer = sendingCustomer;
		this.sendingAccountId = sendingAccountId;
		this.receivingCustomer = receivingCustomer;
		this.receivingAccountId = receivingAccountId;
		this.transferState = TransferState.PENDING;
		this.transferId = transferID;
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
		return "Transfer dateMade " + dateMade + " ammount=" + ammount + ", transferId=" + transferId
				+ ", from " + sendingCustomer + " account " + sendingAccountId+ " to " + receivingCustomer +
				" account " + receivingAccountId + ", transfer is " + transferState.toString();
	}


}
