package com.revature.menus;

import java.util.List;
import java.util.Scanner;

import com.revature.enums.MenuState;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.services.CustomerServiceController;
import com.revature.services.CustomerServiceInterface;
import com.revature.services.EmployeeServiceController;
import com.revature.services.EmployeeServiceInterface;
import com.revature.services.UserServiceController;
import com.revature.services.UserServiceInterface;

public class DisplayController {

	static MenuState menuState = MenuState.NOT_LOGGED_IN;
	static User activeUser;
	static Customer activeCustomer;
	static Employee activeEmployee;
	static UserServiceInterface userServiceManager = new UserServiceController();
	static CustomerServiceInterface customerServiceManager = new CustomerServiceController();
	static EmployeeServiceInterface employeeServiceManager = new EmployeeServiceController();
	static Scanner userInputScanner = new Scanner(System.in);

	// handles all of the exceptions
	// TODO and sanitizes strings

	public static void diplayMenu() {

		switch (menuState) {
		case NOT_LOGGED_IN:
			manageNotLoggedInInput();
			break;
		case MAIN_CUSTOMER_MENU:
			manageCustomerInput();
			break;
		case MAIN_EMPLOYEE_MENU:
			manageEmployeeInput();
			break;
		case CUSTOMER_TRANSFER_MENU:
			manageCustomerTransfers();
			break;

		default:
			break;
		}
	}

	private static void manageNotLoggedInInput() {
		System.out.println("Welcome To Colin's Banking App");

		System.out.println("Please enter a username"); // Output user input

		//TODO exception
		String username = userInputScanner.nextLine(); // Read user input
		System.out.println("Username is: " + username); // Output user input

		System.out.println("Please enter a password");
		String pwd = userInputScanner.nextLine(); // Read user input

		//TODO exception
		User temp = userServiceManager.login(username, pwd);
		if (temp == null) {
			System.out.println("Invalid login");
			// throw new InvalidLoginException();
		} else {
			activeUser = temp;
			System.out.println("Welcome " + activeUser.getUsername());
			if (activeUser instanceof Customer) {
				menuState = MenuState.MAIN_CUSTOMER_MENU;
			} else if (activeUser instanceof Employee) {
				menuState = MenuState.MAIN_EMPLOYEE_MENU;
			}
		}
	}

	private static void manageCustomerInput() {
		System.out.println("CUST");
		activeCustomer = (Customer) activeUser;
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		if (userArgs[0].equals("logout")) {
			logout();
		} else if (userArgs[0].equals("q")) {
			quit();
		} else if (userArgs[0].equals("newcust")) {
			// TODO throw exception for arg length
			// TODO throw boolean exception
			registerNewCustomerAccount(userArgs);
		}
		////////////////////////////////////// CUST specific actions//////////////
		else if (userArgs[0].equals("newacc")) {
			System.out.println("TEMP newacc being made");
			// TODO check for exception
			if (userArgs.length == 2) {
				int initialBalance = Integer.parseInt(userArgs[1]);
				customerServiceManager.applyForBankAccount(activeCustomer, initialBalance);
			} else {
				customerServiceManager.applyForBankAccount(activeCustomer, 0);
			}
		} else if (userArgs[0].equals("viewacc")) {
			List<Account> accounts = customerServiceManager.viewAccounts(activeCustomer, 0);
			for (Account a : accounts) {
				System.out.println(a);
			}
		} else if (userArgs[0].equals("getbal")) {
			System.out.println("CurrentBallance is " + customerServiceManager.viewBalance(activeCustomer, 0));
		} else if (userArgs[0].equals("withdraw")) {
			System.out.println("Withdrawing " + customerServiceManager.withdraw(activeCustomer, 0, 54).getBalance());
		} else if (userArgs[0].equals("deposit")) {
			System.out.println("Depositting " + customerServiceManager.deposit(activeCustomer, 0, 5).getBalance());

		} else if (userArgs[0].equals("transfer")) {
			menuState = MenuState.CUSTOMER_TRANSFER_MENU;
		} else {
			System.out.println("Invalid option");
		}

	}

	private static void manageCustomerTransfers() {
		System.out.println("Choose a Transfer option");
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		if (userArgs[0].equals("self")) {
			customerServiceManager.internalAccountTransfer(activeCustomer, 0, 1, 33);
		} else if (userArgs[0].equals("other")) {
			customerServiceManager.externalAccountTransfer(activeCustomer, 0, "erica", 0, 0);
		} else if (userArgs[0].equals("pending")) {
			List<Transfer> transfers = customerServiceManager.viewPendingTransfers(activeCustomer);
			for (Transfer t : transfers) {
				System.out.println(t);
			}
		} else if (userArgs[0].equals("approve")) {
			customerServiceManager.acceptTransfer(activeCustomer, 0);
		} else if (userArgs[0].equals("decline")) {
			customerServiceManager.declineTransfer(activeCustomer, 0);
		} else if (userArgs[0].equals("logout")) {
			logout();
		} else if (userArgs[0].equals("q")) {
			quit();
		} else if (userArgs[0].equals("back")) {
			System.out.println("Back to main menu");
			menuState = MenuState.MAIN_CUSTOMER_MENU;
		} else {
			System.out.println("Invalid Transfer option");
		}

	}

	private static void manageEmployeeInput() {
		System.out.println("EMP");
		activeEmployee = (Employee) activeUser;
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		if (userArgs[0].equals("logout")) {
			logout();
		} else if (userArgs[0].equals("q")) {
			quit();
		} else if (userArgs[0].equals("newcust")) {
			registerNewCustomerAccount(userArgs);
		}
		////////////////////////////////////// EMP specific actions//////////////
		else if (userArgs[0].equals("viewpendingaccs")) {
			for (Account a : employeeServiceManager.viewPendingAccountsForCustomer("kyle")) {
				System.out.println(a);
			}
		} else if (userArgs[0].equals("viewpendingtransfers")) {
			for (Transfer t : employeeServiceManager.viewPendingTransfersForCustomer("kyle")) {
				System.out.println(t);
			}
		} else if (userArgs[0].equals("viewcust")) {
			System.out.println(employeeServiceManager.viewCustomer("kyle"));
		} else if (userArgs[0].equals("viewlogs")) {
			for (Displayable d : employeeServiceManager.viewTransactionLogs()) {
				System.out.println(d);
			}
		} else if (userArgs[0].equals("approveaccount")) {
			System.out.println("Account approved");
			employeeServiceManager.approveAccount("kyle", 0);
		} else if (userArgs[0].equals("declineaccount")) {
			System.out.println("Account declined");
			employeeServiceManager.declineAccount("kyle", 0);
		} else {
			System.out.println("Invalid option");
		}

	}

	// this is a helper function to reduce code being rewritten
	private static void logout() {
		userServiceManager.logout();
		System.out.println("Logging out");
		activeUser = null;
		activeCustomer = null;
		activeEmployee = null;
		menuState = MenuState.NOT_LOGGED_IN;
	}

	// this is a helper function to reduce code being rewritten
	private static void quit() {
		System.out.println("QUITTING");
		System.exit(0);
	}
	
	private static void registerNewCustomerAccount(String[] userArgs) {
		customerServiceManager.registerNewCustomerAccount(userArgs[1], userArgs[2]);
	}

}
