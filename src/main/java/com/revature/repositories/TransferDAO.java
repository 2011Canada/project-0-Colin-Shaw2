package com.revature.repositories;

import java.util.Collection;

import com.revature.models.Transfer;

public interface TransferDAO {
	
	Transfer addTransfer(Transfer t);

	Transfer updateTransferByID(Transfer c, int id);

	Collection<Transfer> findAllTransfers();
	
	Collection<Transfer> findAllPendingTransfers();
	
	Transfer findTransferByID(int id);


}
