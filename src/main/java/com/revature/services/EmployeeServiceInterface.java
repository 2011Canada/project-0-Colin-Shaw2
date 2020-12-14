package com.revature.services;

import java.util.Collection;
import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface EmployeeServiceInterface {

	Customer viewCustomer(String customerName) throws UserNotFoundException, AccountNotFoundException;
	
	List<Account> viewPendingAccountsForCustomer(String customerName) throws AccountNotFoundException;

	List<Transfer> viewPendingTransfersForCustomer(String customerName) throws AccountNotFoundException;
	
	Collection<String> viewTransactionLogs();
	
	Boolean approveAccount(String customerName, int accountID)throws UnexpectedAccountStateException, AccountNotFoundException, UserNotFoundException;
	
	Boolean declineAccount(String customerName, int accountID)throws UnexpectedAccountStateException, UserNotFoundException, AccountNotFoundException;
	
}
