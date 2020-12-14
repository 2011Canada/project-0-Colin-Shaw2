package com.revature.repositories.postgres;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.UserDAO;

public class PostgressDebugger {
	public static void main(String[] args) {
		UserDAO userDAO = new UserPostgresDAO();
		EmployeeDAO ed = new EmployeePostgresDAO();
		CustomerDAO cd = new CustomerPostgresDAO();
		AccountDAO ad  = new AccountPostgresDAO();
		
		try {
			Customer testCust = new Customer("test", "t");
			Employee testEmp = new Employee("testE", "E");
//			System.out.println(userDAO.findUserByName("colin", true));
			
//			System.out.println(ad.findAllAccountsFromCustomerName("erica"));
			
//			System.out.println(cd.addCustomer(testCust));
//			System.out.println(userDAO.findUserByName(testCust.getUsername(), false));

//			System.out.println(ed.addEmployee(testEmp));
//			System.out.println(ed.findEmployeeByName("colin").getUsername());
//			System.out.println(cd.updateCustomer(new Customer("kyle", "alsdjkfhaosifufop")).getUsername());
//			System.out.println(cd.updateCustomer(new Customer("colisn", "alsdjkfhaosifufop")).getUsername());

//			System.out.println(userDAO.findUserByName("colin", true).getUsername());
//			System.out.println(cd.findCustomerByName("erica").getAccounts());
//			System.out.println(userDAO.findUserByName("kyle", false).getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
