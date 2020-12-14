package com.revature.services;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface CustomerServiceInterface{
	
	Customer applyForBankAccount(Customer currentCustomer, long initialBalance) throws UserNotFoundException;
	
	List<Account> viewAccounts(Customer currentCustomer) throws UserNotFoundException, AccountNotFoundException;
	
	long viewBalance(Customer currentCustomer, int accountID) throws AccountNotFoundException;
	
	//returns amount withdrawn
	Account withdraw(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;
	
	//returns amount deposited
	Account deposit(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;

	List<Account> internalAccountTransfer(Customer currentCustomer, int fromAccountID, int toAccountID,
			int amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;
	
	Transfer externalAccountTransfer(Customer currentCustomer, int fromAccountID, String toCustomerName, int toAccountID, int amount) throws UserNotFoundException, AccountNotFoundException;

	Transfer acceptTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException;

	Transfer declineTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException ;
	
	List<Transfer> viewPendingTransfers(Customer currentCustomer) throws AccountNotFoundException;
	
}
