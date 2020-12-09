package com.revature.models;

import java.util.Date;

import com.revature.enums.TransferState;

public class Transfer {
	Date dateMade;
	int ammount;
	int transferId;
	Customer sendingCustomer;
	Customer receivingCustomer;
	Enum<TransferState> transferState;
	
	public Transfer(Date dateMade, int ammount, int transferId, Customer sendingCustomer, Customer receivingCustomer) {
		this.dateMade = dateMade;
		this.ammount = ammount;
		this.transferId = transferId;
		this.sendingCustomer = sendingCustomer;
		this.receivingCustomer = receivingCustomer;
		this.transferState = TransferState.PENDING;
	}


}
