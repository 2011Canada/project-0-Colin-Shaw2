package com.revature.services;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface CustomerServiceInterface{
	
	Customer applyForBankAccount(Customer currentCustomer, long initialBalance);
	
	List<Account> viewAccounts(Customer currentCustomer, int accountID) throws UserNotFoundException;
	
	long viewBalance(Customer currentCustomer, int accountID) throws AccountNotFoundException;
	
	//returns amount withdrawn
	Account withdraw(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException, AccountNotFoundException;
	
	//returns amount deposited
	Account deposit(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException, AccountNotFoundException;

	Boolean internalAccountTransfer(Customer currentCustomer, int fromAccountID, int toAccountID,
			int amount) throws NegativeBalanceException, AccountNotFoundException;
	
	Boolean externalAccountTransfer(Customer currentCustomer, int fromAccountID, String toCustomerName, int toAccountID, int amount) throws UserNotFoundException;

	Boolean acceptTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException, AccountNotFoundException ;

	Boolean declineTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException, AccountNotFoundException ;
	
	List<Transfer> viewPendingTransfers(Customer currentCustomer) throws AccountNotFoundException;
	
}
