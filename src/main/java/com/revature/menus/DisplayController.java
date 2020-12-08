package com.revature.menus;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.FileDAO;

public class DisplayController {

	static User activeUser;
	static FileDAO dao = new FileDAO();
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
		User temp = dao.findUserByName(username);
		if(temp == null) {
			System.out.println("Invalid login");
		}
		else if(temp.getPassword().equals(pwd)){
			System.out.println("Successfull Login");
			activeUser = temp;
		}
		else {
			System.out.println("Invalid login else");
			// throw new InvalidLoginException();
		}
		manageUserInput();
		
	}

	private static void manageLoggedInInput() {
		System.out.println("TEMP NOW EXECPTING USER INPUT");
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input
		System.out.println(userArgs[0]);
		
		if(activeUser instanceof Customer){
			manageCustomerInput();
		}else if(activeUser instanceof Employee){
			manageEmployeeInput();
		}
		if(userArgs[0].equals("logout")) {
			System.out.println("TEMP Logging out");
			activeUser = null;
		}
		else if(userArgs[0].equals("q")) {
			System.exit(0);
		}
		else if(userArgs[0].equals("newacc")) {
			
		}
		else if(userArgs[0].equals("getbal")) {
			System.out.println("TEMP CurrentBallance is");
			if(activeUser instanceof Customer) {
				
			}
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
		
		
		if (activeUser instanceof Customer) {
//			manageCustomerInput();
			DisplayController.displayWelcome();
		} else if (activeUser instanceof Employee) {
//			manageEmployeeInput();
		}

		manageUserInput();

	}

	private static void manageCustomerInput() {
		String input = userInputScanner.nextLine(); // Read user input
		
		if(input.equals("logout")) {
			activeUser = null;
		}
		else if(input.equals("newacc")) {}
		else if(input.equals("getbal")) {}
		else if(input.equals("withdraw")) {}
		else if(input.equals("deposit")) {}
		else if(input.equals("transfer")) {}
		else if(input.equals("acceptTransfer")) {}
		else {System.out.println("Invalid option");}
		
		
		// login
		// registernewaccount
		// getbalance
		// withdraw
		// deposit
		// transfer
		// accepttransfer
	}

	private static void manageEmployeeInput() {
		String input = userInputScanner.nextLine(); // Read user input

		// login
		// registernewaccount
		// approveaccount
		// viewBalance
		// viewlogs
	}

}
