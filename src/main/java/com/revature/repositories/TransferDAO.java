package com.revature.repositories;

import java.sql.SQLException;
import java.util.Collection;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Transfer;

public interface TransferDAO {
	
	Transfer addTransfer(Transfer t)  throws SQLException, TransferNotFoundException, UserNotFoundException, AccountNotFoundException;

	Transfer updateTransferByID(Transfer c, int id);

	Collection<Transfer> findAllTransfers();
	
	Collection<Transfer> findAllTransfersForCustomer(String username) throws AccountNotFoundException;
	
	Collection<Transfer> findAllPendingTransfers() throws AccountNotFoundException;
	
	Collection<Transfer> findAllPendingTransfersForCustomer(String username) throws AccountNotFoundException;
	
	Transfer findTransferByID(int id) throws TransferNotFoundException;


}
