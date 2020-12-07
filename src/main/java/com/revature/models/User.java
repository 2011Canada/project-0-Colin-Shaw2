package com.revature.models;

import com.revature.repositories.UserDAO;
import com.revature.repositories.fileDAO;
import com.revature.services.UserServiceInterface;

public abstract class User implements UserServiceInterface {
	private int userID;
	
	static private UserDAO dao = new fileDAO();
	
	User(int id){
		this.userID = id;
	}
	
	//can be null
	public User login(String username, String password) {
		User u = dao.findUserByName(username);
		return u;
	}

	public User registerNewAcount(String username, String password, long acountBalance) {
		// TODO Auto-generated method stub
		return null;
	}public User registerNewAcount(String username, String password) {
		return registerNewAcount(username, password, 0);
	}
}
