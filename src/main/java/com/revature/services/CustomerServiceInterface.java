package com.revature.services;

import java.util.List;

import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface CustomerServiceInterface extends UserServiceInterface{
	
	Customer applyForBankAccount(Customer currentCustomer, long initialBalance);
	
	List<Account> viewAccounts(Customer currentCustomer, int accountID);
	
	long viewBalance(Customer currentCustomer, int accountID);
	
	//returns amount withdrawn
	Account withdraw(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException;
	
	//returns amount deposited
	Account deposit(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException;

	Boolean internalAccountTransfer(Customer currentCustomer, int fromAccountID, int toAccountID,
			int amount) throws NegativeBalanceException;
	
	Boolean externalAccountTransfer(Customer currentCustomer, int fromAccountID, String toCustomerName, int toAccountID, int amount);

	Boolean acceptTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException ;

	Boolean declineTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException ;
	
	List<Transfer> viewPendingTransfers(Customer currentCustomer);
	
}
