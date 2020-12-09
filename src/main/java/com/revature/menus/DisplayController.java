package com.revature.menus;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.UserFileDAO;
import com.revature.services.CustomerServiceController;
import com.revature.services.CustomerServiceInterface;
import com.revature.services.UserServiceController;
import com.revature.services.UserServiceInterface;

public class DisplayController {

	static User activeUser;
	static Customer activeCustomer;
	static Employee activeEmployee;
	static UserServiceInterface userServiceManager = new UserServiceController();
	static CustomerServiceInterface customerServiceManager = new CustomerServiceController();
	static Scanner userInputScanner = new Scanner(System.in);

	// handles all of the exceptions
	// and sanitizes strings

	public static void displayWelcome() {
		System.out.println("Welcome To Colin's Banking App");

	}

	public static void manageUserInput() {
		if(null == activeUser) {
			manageNotLoggedInInput();
		}else {
			manageLoggedInInput();
		}

	}
	
	private static void manageNotLoggedInInput() {

		System.out.println("Please enter a username"); // Output user input

		String username = userInputScanner.nextLine(); // Read user input
		System.out.println("Username is: " + username); // Output user input

		System.out.println("Please enter a password");
		String pwd = userInputScanner.nextLine(); // Read user input
		System.out.println("Password is: " + pwd); // Output user input

		// check DB for user password combo
		// TODO temp
		
		User temp = userServiceManager.login(username, pwd);
		if(temp == null) {
			System.out.println("Invalid login");
			// throw new InvalidLoginException();
		}
		else {
			activeUser = temp;
			System.out.println("Welcome " + activeUser.getUsername());
		}
		manageUserInput();
		
	}

	private static void manageLoggedInInput() {
		
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input
		
		System.out.println("CHOOSING CUST OR EMP OR USER COMMAND");
		if(userArgs[0].equals("logout")) {
			userServiceManager.logout();
			System.out.println("Logging out");
			activeUser = null;
		}
		else if(userArgs[0].equals("q")) {
			System.out.println("QUITTING");
			System.exit(0);
		}
		else if(userArgs[0].equals("newcust")) {
			//TODO throw exception for arg length
			//TODO throw boolean exception
			userServiceManager.registerNewCustomerAccount(userArgs[1], userArgs[2]);
		}
		else if(activeUser instanceof Customer){
			manageCustomerInput(userArgs);
		}else if(activeUser instanceof Employee){
			manageEmployeeInput(userArgs);
		}

		manageUserInput();

	}

	private static void manageCustomerInput(String[] userArgs) {
		System.out.println("CUST");
			
		
		if(userArgs[0].equals("newacc")) {
			System.out.println("TEMP newacc being made");
			//TODO check for execption
			if(userArgs.length == 2) {
				int initialBalance = Integer.parseInt(userArgs[1]);
				customerServiceManager.applyForBankAccount((Customer)activeUser, initialBalance);				
			}
			customerServiceManager.applyForBankAccount((Customer)activeUser, 0);
		}
		else if(userArgs[0].equals("getbal")) {
			System.out.println("TEMP CurrentBallance is");
			customerServiceManager.viewBalance((Customer)activeUser, 0);
		}
		else if(userArgs[0].equals("withdraw")) {
			
		}
		else if(userArgs[0].equals("deposit")) {
			
		}
		else if(userArgs[0].equals("transfer")) {
			
		}
		else if(userArgs[0].equals("acceptTransfer")) {
			
		}
		else {System.out.println("Invalid option");}
		
		
		
	}

	private static void manageEmployeeInput(String[] userArgs) {
		System.out.println("EMP");
		
//		if(userArgs[0].equals("logout")) {
//			System.out.println("TEMP Logging out");
//			activeUser = null;
//		}
//		else if(userArgs[0].equals("q")) {
//			System.out.println("QUITTING");
//			System.exit(0);
//		}
//		else if(userArgs[0].equals("newcust")) {
//			
//		}
//		else if(userArgs[0].equals("newacc")) {
//			
//		}
//		
		
		// login
		// registernewaccount
		// approveaccount
		// viewBalance
		// viewlogs
	}

}
