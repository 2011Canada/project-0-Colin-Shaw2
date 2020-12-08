package com.revature.models;

import com.revature.repositories.UserDAO;
import com.revature.repositories.fileDAO;
import com.revature.services.UserServiceInterface;

public abstract class User implements UserServiceInterface {
	private int userID;
	private String username;
	private String password;
	
	static private UserDAO dao = new fileDAO();

	User(){
		this.userID = 0;
		this.username = "user";
		this.password = "user";
	}

	User(int id){
		this.userID = id;
		this.username = "user";
		this.password = "user";
	}

	User(int id, String username, String password){
		this.userID = id;
		this.username = username;
		this.password = password;
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
