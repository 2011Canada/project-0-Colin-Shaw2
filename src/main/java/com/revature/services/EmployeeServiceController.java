package com.revature.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.TransferDAO;
import com.revature.repositories.file.AccountFileDAO;
import com.revature.repositories.file.CustomerFileDAO;
import com.revature.repositories.file.TransferFileDAO;

public class EmployeeServiceController implements EmployeeServiceInterface {

	private static CustomerDAO custDAO = new CustomerFileDAO();
	private static AccountDAO accDAO = new AccountFileDAO();
	private static TransferDAO transDAO = new TransferFileDAO();
	private static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");

	@Override
	public Customer viewCustomer(String customerName) throws UserNotFoundException, AccountNotFoundException, SQLException {
		eventLogger.info("viewCustomer "  + customerName);
		return custDAO.findCustomerByName(customerName);
	}

	@Override
	public List<Account> viewPendingAccountsForCustomer(String customerName) throws AccountNotFoundException, SQLException {
		eventLogger.info("viewPendingAccountsForCustomer "  + customerName);
		return accDAO.findAllAccountsFromCustomerName(customerName);
	}

	@Override
	public List<Transfer> viewPendingTransfersForCustomer(String customerName) throws AccountNotFoundException, SQLException, UserNotFoundException {
		eventLogger.info("viewPendingAccountsForCustomer "  + customerName);
		return (List<Transfer>) transDAO.findAllPendingTransfersForCustomer(customerName);
	}

	@Override
	// this does not use a DAO for simplicity
	public Collection<String> viewTransactionLogs() {
		eventLogger.info("viewTransactionLogs");
		BufferedReader reader;
		Collection<String> allTransactions = new LinkedList<>();
		try {
			reader = new BufferedReader(new FileReader("transactions.log"));
			String line = reader.readLine();
			while (line != null) {
				allTransactions.add(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allTransactions;
				
	}

	@Override
	public Boolean approveAccount(String customerName, int accountID) throws UnexpectedAccountStateException, AccountNotFoundException, UserNotFoundException, SQLException {
		eventLogger.info("approveAccount "  + customerName + " " + accountID);
		Customer customer = custDAO.findCustomerByName(customerName);
		Account a = accDAO.findAccountByCustomerandID(customer, accountID);
		a.approveAccount();
		accDAO.updateAccountByCustomerandID(customer, accountID, a);
		return null;
	}

	@Override
	public Boolean declineAccount(String customerName, int accountID) throws UnexpectedAccountStateException, UserNotFoundException, AccountNotFoundException, SQLException {
		eventLogger.info("declineAccount "  + customerName +  " " + accountID);
		Customer customer = custDAO.findCustomerByName(customerName);
		Account a = accDAO.findAccountByCustomerandID(customer, accountID);
		a.declineAccount();
		accDAO.updateAccountByCustomerandID(customer, accountID, a);
		return null;
	}

}
