package com.revature.models;

import com.revature.menus.Displayable;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserFileDAO;
import com.revature.services.UserServiceInterface;

public abstract class User implements Displayable{

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


	@Override
	public String display() {
		//TODO
		return toString();
	}
}
