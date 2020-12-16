package com.revature.repositories;

import java.sql.SQLException;
import java.util.Collection;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Transfer;

public interface TransferDAO {
	
	Transfer addTransfer(Transfer t)  throws SQLException, TransferNotFoundException, UserNotFoundException, AccountNotFoundException;

	Transfer updateTransferByID(Transfer c, int id) throws SQLException, AccountNotFoundException;
	
	Collection<Transfer> findAllPendingTransfersForCustomer(String username) throws AccountNotFoundException, SQLException, UserNotFoundException, TransferNotFoundException;
	
	Transfer findTransferByID(int id) throws TransferNotFoundException, SQLException, UserNotFoundException, AccountNotFoundException;


}
