package com.revature.repositories;

public class PostgressDebugger {
	public static void main(String[] args) {
		UserDAO userDAO = new UserPostgresDAO();
		
		try {
			System.out.println(userDAO.findUserByName("colin").getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
