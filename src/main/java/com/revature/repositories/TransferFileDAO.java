package com.revature.repositories;

import java.util.ArrayList;
import java.util.Collection;

import com.revature.enums.TransferState;
import com.revature.models.Transfer;

public class TransferFileDAO implements TransferDAO {
	
	@Override
	public Transfer addTransfer(Transfer t) {
		FakeFileDB.transfer.put(t.getTransferId(), t);
		return t;
	}

	@Override
	public Transfer updateTransferByID(Transfer c, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Transfer> findAllTransfers() {
		return FakeFileDB.transfer.values();
	}
	
	@Override
	public Collection<Transfer> findAllPendingTransfers() {
		
		Collection<Transfer> pending = new ArrayList<>();
		
		for (Transfer t : FakeFileDB.transfer.values()) {
			if(t.getTransferState().equals(TransferState.PENDING)) {
				pending.add(t);
			}
		}

		return pending;
	}

	@Override
	public Transfer findTransferByID(Transfer c, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
