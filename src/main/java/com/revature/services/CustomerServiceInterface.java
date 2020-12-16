package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;

public interface CustomerServiceInterface{
	
	Account applyForBankAccount(Customer currentCustomer, long initialBalance) throws UserNotFoundException, SQLException, NegativeBalanceException, AccountNotFoundException;
	
	List<Account> viewAccounts(Customer currentCustomer) throws UserNotFoundException, AccountNotFoundException, SQLException;
	
	long viewBalance(Customer currentCustomer, int accountID) throws AccountNotFoundException, SQLException;
	
	//returns amount withdrawn
	Account withdraw(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException, UnexpectedAccountStateException;
	
	//returns amount deposited
	Account deposit(Customer currentCustomer, int accountID, int amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException, UnexpectedAccountStateException;

	List<Account> internalAccountTransfer(Customer currentCustomer, int fromAccountID, int toAccountID,
			int amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException, SQLException, UnexpectedAccountStateException;
	
	Transfer externalAccountTransfer(Customer currentCustomer, int fromAccountID, String toCustomerName, int toAccountID, int amount) throws UserNotFoundException, AccountNotFoundException, SQLException, TransferNotFoundException;

	Transfer acceptTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException, SQLException, UserNotFoundException, NegativeBalanceException, UnexpectedAccountStateException;

	Transfer declineTransfer(Customer currentCustomer, int transferID)throws UnexpectedTransferStateException, AccountNotFoundException, TransferNotFoundException, SQLException, UserNotFoundException ;
	
	List<Transfer> viewPendingTransfers(Customer currentCustomer) throws AccountNotFoundException, SQLException, UserNotFoundException, TransferNotFoundException;
	
}
