package com.revature.models;

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
