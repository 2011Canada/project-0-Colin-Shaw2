package com.revature.repositories.file;

import java.util.ArrayList;
import java.util.Collection;

import com.revature.enums.TransferState;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.models.Transfer;
import com.revature.repositories.TransferDAO;

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

//	@Override
//	public Collection<Transfer> findAllPendingTransfers() {
//		
//		Collection<Transfer> pending = new ArrayList<>();
//		
//		for (Transfer t : FakeFileDB.transfer.values()) {
//			if(t.getTransferState().equals(TransferState.PENDING)) {
//				pending.add(t);
//			}
//		}
//
//		return pending;
//	}
	
	@Override
	public Collection<Transfer> findAllPendingTransfersForCustomer(String username){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transfer findTransferByID(int id) throws TransferNotFoundException {
		if(FakeFileDB.transfer.get(id) == null) {
			throw new TransferNotFoundException();
		}
		return FakeFileDB.transfer.get(id);
	}

}
