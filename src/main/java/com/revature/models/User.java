package com.revature.models;

import com.revature.repositories.UserDAO;
import com.revature.repositories.UserFileDAO;
import com.revature.services.UserServiceInterface;

public abstract class User{

	private String username;
	private String password;
	

	User(){
		this.username = "default";
		this.password = "default";
	}


	User(String username, String password){
		this.username = username;
		this.password = password;
	}
	

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
