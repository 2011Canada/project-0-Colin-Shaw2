package com.revature.services;

import java.util.Collection;
import java.util.List;

import com.revature.menus.Displayable;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface EmployeeServiceInterface {

//	List<Account> viewAccounts(Customer currentCustomer);
//	
	Customer viewCustomer(String customerName);
	
	List<Account> viewPendingAccountsForCustomer(String customerName);

	List<Transfer> viewPendingTransfersForCustomer(String customerName);
//	
//	List<Transfer> viewAllTransfers(Customer currentCustomer);
//	
//	List<Transfer> viewDeclinedTransfers(Customer currentCustomer);
//	
//	List<Transfer> viewApprovedTransfers(Customer currentCustomer);
	
	Collection<Displayable> viewTransactionLogs();
	
	Boolean approveAccount(String customerName, int accountID);
	
	Boolean declineAccount(String customerName, int accountID);
	
}
