package com.revature.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface EmployeeServiceInterface {

	Customer viewCustomer(String customerName) throws UserNotFoundException, AccountNotFoundException, SQLException;
	
	List<Account> viewPendingAccountsForCustomer(String customerName) throws AccountNotFoundException, SQLException;

	List<Transfer> viewPendingTransfersForCustomer(String customerName) throws AccountNotFoundException, SQLException, UserNotFoundException, TransferNotFoundException;
	
	Collection<String> viewTransactionLogs() throws  IOException;
	
	Boolean approveAccount(String customerName, int accountID)throws UnexpectedAccountStateException, AccountNotFoundException, UserNotFoundException, UnexpectedAccountStateException, SQLException;
	
	Boolean declineAccount(String customerName, int accountID)throws UnexpectedAccountStateException, UserNotFoundException, AccountNotFoundException, SQLException;
	
}
