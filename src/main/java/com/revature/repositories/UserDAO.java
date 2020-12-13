package com.revature.repositories;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDAO {

	public User findUserByName(String s)throws UserNotFoundException;

	
}
