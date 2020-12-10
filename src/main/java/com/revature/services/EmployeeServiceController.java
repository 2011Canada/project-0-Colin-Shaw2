package com.revature.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.revature.menus.Displayable;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transfer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountFileDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerFileDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeFileDAO;
import com.revature.repositories.TransferDAO;
import com.revature.repositories.TransferFileDAO;

public class EmployeeServiceController implements EmployeeServiceInterface {

	private static EmployeeDAO empDAO = new EmployeeFileDAO();
	private static CustomerDAO custDAO = new CustomerFileDAO();
	private static AccountDAO accDAO = new AccountFileDAO();
	private static TransferDAO transDAO = new TransferFileDAO();
	
	@Override
	public Customer viewCustomer(String customerName) {
		return custDAO.findCustomerByName(customerName);
	}

	@Override
	public List<Account> viewPendingAccountsForCustomer(String customerName) {
		return Arrays.asList(accDAO.findAllAccountsFromCustomerName(customerName));
	}

	@Override
	public List<Transfer> viewPendingTransfersForCustomer(String customerName) {
		return (List<Transfer>) transDAO.findAllPendingTransfersForCustomer(customerName);
	}

	@Override
	public Collection<Displayable> viewTransactionLogs() {
		//TODO figure this out
		return null;
	}

	@Override
	public Boolean approveAccount(String customerName, int accountID) {
		Customer customer = custDAO.findCustomerByName(customerName);
		Account a = accDAO.findAccountByCustomerandID(customer, accountID);
		a.approveAccount();
		accDAO.updateAccountByCustomerandID(customer, accountID, a);
		return null;
	}

	@Override
	public Boolean declineAccount(String customerName, int accountID) {
		Customer customer = custDAO.findCustomerByName(customerName);
		Account a = accDAO.findAccountByCustomerandID(customer, accountID);
		a.declineAccount();
		accDAO.updateAccountByCustomerandID(customer, accountID, a);
		return null;
	}

}
