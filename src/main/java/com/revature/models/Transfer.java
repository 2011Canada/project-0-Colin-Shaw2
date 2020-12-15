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
			int receivingAccountId, int transferID, TransferState transferState) {
		this.dateMade = new Date();
		this.ammount = ammount;
		this.sendingCustomer = sendingCustomer;
		this.sendingAccountId = sendingAccountId;
		this.receivingCustomer = receivingCustomer;
		this.receivingAccountId = receivingAccountId;
		this.transferState = transferState;
		this.transferId = transferID;
	}
	
	public Date getDateMade() {
		return dateMade;
	}
	
	
	public int getAmmount() {
		return ammount;
	}
	
	
	public Customer getSendingCustomer() {
		return sendingCustomer;
	}
	
	
	public int getSendingAccountId() {
		return sendingAccountId;
	}
	
	
	public Customer getReceivingCustomer() {
		return receivingCustomer;
	}
	
	
	public int getReceivingAccountId() {
		return receivingAccountId;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int int1) {
		this.transferId = int1;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ammount;
		result = prime * result + ((dateMade == null) ? 0 : dateMade.hashCode());
		result = prime * result + receivingAccountId;
		result = prime * result + ((receivingCustomer == null) ? 0 : receivingCustomer.hashCode());
		result = prime * result + sendingAccountId;
		result = prime * result + ((sendingCustomer == null) ? 0 : sendingCustomer.hashCode());
		result = prime * result + transferId;
		result = prime * result + ((transferState == null) ? 0 : transferState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transfer other = (Transfer) obj;
		if (ammount != other.ammount)
			return false;
		if (dateMade == null) {
			if (other.dateMade != null)
				return false;
		} else if (!dateMade.equals(other.dateMade))
			return false;
		if (receivingAccountId != other.receivingAccountId)
			return false;
		if (receivingCustomer == null) {
			if (other.receivingCustomer != null)
				return false;
		} else if (!receivingCustomer.equals(other.receivingCustomer))
			return false;
		if (sendingAccountId != other.sendingAccountId)
			return false;
		if (sendingCustomer == null) {
			if (other.sendingCustomer != null)
				return false;
		} else if (!sendingCustomer.equals(other.sendingCustomer))
			return false;
		if (transferId != other.transferId)
			return false;
		if (transferState != other.transferState)
			return false;
		return true;
	}



}
