package com.revature.repositories.file;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

public class UserFileDAO implements UserDAO {
	

	static HashMap<String, User> db = FakeFileDB.db;

	public UserFileDAO() {
		// TODO Auto-generated constructor stub
	}

	
//	@Override
//	public Employee updateEmployee(Employee u) throws UserNotFoundException{
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	public User updateUser(User u) throws UserNotFoundException{
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public List<User> findAllUsers() throws UserNotFoundException{
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public User findUserByName(String s, Boolean isEmployee) throws UserNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	


}
