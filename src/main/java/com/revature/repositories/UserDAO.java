package com.revature.repositories;

import java.sql.SQLException;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDAO {

	public User findUserByName(String s, Boolean isEmployee) throws UserNotFoundException, SQLException, AccountNotFoundException;

	
}
