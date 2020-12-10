package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface EmployeeServiceInterface {

//	List<Account> viewAccounts(Customer currentCustomer);
//	
	Account viewAccount(Customer currentCustomer, int accountID);
	
	List<Account> viewPendingAccounts(int accountID);

	List<Transfer> viewPendingTransfers(Customer currentCustomer);
//	
//	List<Transfer> viewAllTransfers(Customer currentCustomer);
//	
//	List<Transfer> viewDeclinedTransfers(Customer currentCustomer);
//	
//	List<Transfer> viewApprovedTransfers(Customer currentCustomer);
	
	List<String> viewTransactionLogs();
	
	Boolean approveAccount(Customer currentCustomer, int accountID);
	
	Boolean declineAccount(Customer currentCustomer, int accountID);
	
}
