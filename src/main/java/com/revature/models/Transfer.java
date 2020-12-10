package com.revature.models;

import java.util.Date;

import com.revature.enums.TransferState;

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
	
	public void approveTransfer() {
		if(transferState.equals(TransferState.PENDING)) {
			transferState = TransferState.APPROVED;
		}else {
			//TODO execption
		}
	}
	public void declineTransfer() {
		if(transferState.equals(TransferState.PENDING)) {
			transferState = TransferState.DENIED;
		}else {
			//TODO execption
		}
	}

	@Override
	public String toString() {
		return "Transfer [dateMade=" + dateMade + ", ammount=" + ammount + ", transferId=" + transferId
				+ ", sendingCustomer=" + sendingCustomer + ", receivingCustomer=" + receivingCustomer
				+ ", transferState=" + transferState + "]";
	}


}
