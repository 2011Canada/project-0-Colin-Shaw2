package com.revature.repositories;

import java.sql.SQLException;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public interface CustomerDAO{

	public Customer addCustomer(Customer c) throws AccountNotFoundException, TransferNotFoundException, UserNotFoundException, SQLException;

	public Customer updateCustomer(Customer c) throws UserNotFoundException, SQLException;
		
	public Customer findCustomerByName(String username) throws UserNotFoundException, AccountNotFoundException, SQLException;
	
	
}
