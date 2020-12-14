package com.revature.repositories;

import com.revature.models.Customer;

public class PostgressDebugger {
	public static void main(String[] args) {
		UserDAO userDAO = new UserPostgresDAO();
		CustomerDAO cd = new CustomerPostgresDAO();
		AccountDAO ad  = new AccountPostgresDAO();
		
		try {
			Customer testCust = new Customer("test", "t");
//			System.out.println(userDAO.findUserByName("colin", true));
			
//			System.out.println(ad.findAllAccountsFromCustomerName("erica"));
			
//			System.out.println(cd.addCustomer(testCust));
//			System.out.println(userDAO.findUserByName(testCust.getUsername(), false));
			

			System.out.println(userDAO.findUserByName("colin", true).getUsername());
			System.out.println(cd.findCustomerByName("erica").getAccounts());
			System.out.println(userDAO.findUserByName("kyle", false).getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
