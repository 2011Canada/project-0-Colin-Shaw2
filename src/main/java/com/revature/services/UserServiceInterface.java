package com.revature.services;

import java.sql.SQLException;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;

public interface UserServiceInterface {

	public User login(String username, String password, Boolean isEmployee) throws UserNotFoundException, SQLException, AccountNotFoundException;

	public void logout(String username);

	public Customer registerNewCustomerAccount(String username, String password) throws AccountNotFoundException, TransferNotFoundException, UserNotFoundException;

	
}
