package com.revature.services;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface CustomerServiceInterface extends UserServiceInterface{
	
	Customer applyForBankAccount(Customer currentCustomer, long initialBalance);
	
	Account viewBalance(Customer currentCustomer, int accountID);
	
	Account withdraw(Customer currentCustomer, int accountID);
	
	Account deposit(Customer currentCustomer, int accountID);

	Boolean internalAccountTransfer(Customer currentCustomer, int fromAccountID, int toAccountID);
	
	Boolean externalAccountTransfer(Customer currentCustomer, int fromAccountID, Customer toAccount,int toAccountID);
	
	Boolean acceptTransfer(Customer currentCustomer, int transferID);
	
	ArrayList<Transfer> viewPendingTransfers(Customer currentCustomer);
	
}
