package com.revature.services;

import java.util.Collection;
import java.util.List;

import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface EmployeeServiceInterface {

	Customer viewCustomer(String customerName);
	
	List<Account> viewPendingAccountsForCustomer(String customerName);

	List<Transfer> viewPendingTransfersForCustomer(String customerName);
	
	Collection<String> viewTransactionLogs();
	
	Boolean approveAccount(String customerName, int accountID)throws UnexpectedAccountStateException;
	
	Boolean declineAccount(String customerName, int accountID)throws UnexpectedAccountStateException;
	
}
