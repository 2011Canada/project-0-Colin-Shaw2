package com.revature.services;

import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface CustomerServiceInterface extends UserServiceInterface{
	
	Customer applyForBankAccount(Customer currentCustomer, long initialBalance);
	
	long viewBalance(Customer currentCustomer, int accountID);
	
	//returns amount withdrawn
	Account withdraw(Customer currentCustomer, int accountID, int amount);
	
	//returns amount deposited
	Account deposit(Customer currentCustomer, int accountID, int amount);

	Boolean internalAccountTransfer(Customer currentCustomer, int fromAccountID, int toAccountID);
	
	Boolean externalAccountTransfer(Customer currentCustomer, int fromAccountID, Customer toAccount,int toAccountID);
	
	Boolean acceptTransfer(Customer currentCustomer, int transferID);
	
	ArrayList<Transfer> viewPendingTransfers(Customer currentCustomer);
	
}
