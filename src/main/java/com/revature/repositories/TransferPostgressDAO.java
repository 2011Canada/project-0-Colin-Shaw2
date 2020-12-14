package com.revature.repositories;

import java.util.Collection;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.models.Transfer;

public class TransferPostgressDAO implements TransferDAO {

	@Override
	public Transfer addTransfer(Transfer t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transfer updateTransferByID(Transfer c, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Transfer> findAllTransfers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Transfer> findAllTransfersForCustomer(String username) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Transfer> findAllPendingTransfers() throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Transfer> findAllPendingTransfersForCustomer(String username) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transfer findTransferByID(int id) throws TransferNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
