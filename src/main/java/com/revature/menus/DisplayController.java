package com.revature.menus;

import java.util.List;
import java.util.Scanner;

import com.revature.enums.MenuState;
import com.revature.exceptions.InvalidArgumentLengthException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.exceptions.UnexpectedTransferStateException;
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

	// TODO make menu give more info
	public static void diplayMenu() {

		try {
			switch (menuState) {
			case NOT_LOGGED_IN:
				showNotLoggedInMenu();
				break;
			case MAIN_CUSTOMER_MENU:
				showCustomerInputMenu();
				break;
			case MAIN_EMPLOYEE_MENU:
				showEmployeeInputMenu();
				break;
			case CUSTOMER_TRANSFER_MENU:
				showCustomerTransfersMenu();
				break;

			default:
				break;
			}
		} catch (InvalidArgumentLengthException e) {
			System.out.println(e.getMessage());
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.print("Ooops we expected an integer ");
			System.out.println(e.getMessage());
		} catch (UnexpectedAccountStateException e) {
			System.out.println(e.getMessage());
		} catch (UnexpectedTransferStateException e) {
			System.out.println(e.getMessage());
		} catch (NegativeBalanceException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void showNotLoggedInMenu() throws UserNotFoundException {
		System.out.println("Welcome To Colin's Banking App");

		System.out.println("Please enter a username"); // Output user input

		String username = userInputScanner.nextLine(); // Read user input
		System.out.println("Username is: " + username); // Output user input

		System.out.println("Please enter a password");
		String pwd = userInputScanner.nextLine(); // Read user input

		User temp = userServiceManager.login(username, pwd);
		
		
		activeUser = temp;
		System.out.println("Welcome " + activeUser.getUsername());
		if (activeUser instanceof Customer) {
			menuState = MenuState.MAIN_CUSTOMER_MENU;
		} else if (activeUser instanceof Employee) {
			menuState = MenuState.MAIN_EMPLOYEE_MENU;
		}
	}

	private static void showCustomerInputMenu() throws InvalidArgumentLengthException, NumberFormatException,
			UnexpectedTransferStateException, NegativeBalanceException {
		System.out.println("CUST");
		activeCustomer = (Customer) activeUser;
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		if (0 == userArgs.length) {
			throw new InvalidArgumentLengthException(1, 0);
		}
		if (userArgs[0].equals("logout")) {
			logout();
		} else if (userArgs[0].equals("q")) {
			quit();
		} else if (userArgs[0].equals("newcust")) {
			registerNewCustomerAccount(userArgs);
		}
		////////////////////////////////////// CUST specific actions//////////////
		else if (userArgs[0].equals("newacc")) {
			checkInputLength(2, userArgs.length);
			customerServiceManager.applyForBankAccount(activeCustomer, Integer.parseInt(userArgs[1]));
			System.out.println("TEMP newacc being made");

		} else if (userArgs[0].equals("viewacc")) {
			checkInputLength(2, userArgs.length);
			List<Account> accounts = customerServiceManager.viewAccounts(activeCustomer, Integer.parseInt(userArgs[1]));
			for (Account a : accounts) {
				System.out.println(a);
			}
		} else if (userArgs[0].equals("getbal")) {
			checkInputLength(2, userArgs.length);
			System.out.println("CurrentBallance is "
					+ customerServiceManager.viewBalance(activeCustomer, Integer.parseInt(userArgs[1])));
		} else if (userArgs[0].equals("withdraw")) {
			checkInputLength(3, userArgs.length);
			System.out.println("Withdrawing " + customerServiceManager
					.withdraw(activeCustomer, Integer.parseInt(userArgs[1]), Integer.parseInt(userArgs[2]))
					.getBalance());
		} else if (userArgs[0].equals("deposit")) {
			checkInputLength(3, userArgs.length);
			System.out.println("Depositting " + customerServiceManager
					.deposit(activeCustomer, Integer.parseInt(userArgs[1]), Integer.parseInt(userArgs[2]))
					.getBalance());
		} else if (userArgs[0].equals("transfer")) {
			checkInputLength(1, userArgs.length);
			menuState = MenuState.CUSTOMER_TRANSFER_MENU;
		} else {
			System.out.println("Invalid option");
		}

	}

	private static void showCustomerTransfersMenu() throws InvalidArgumentLengthException, NumberFormatException,
			UnexpectedTransferStateException, NegativeBalanceException {
		System.out.println("Choose a Transfer option");
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		checkInputLength(1, userArgs.length);
		if (userArgs[0].equals("self")) {
			checkInputLength(4, userArgs.length);
			customerServiceManager.internalAccountTransfer(activeCustomer, Integer.parseInt(userArgs[1]),
					Integer.parseInt(userArgs[2]), Integer.parseInt(userArgs[3]));
		} else if (userArgs[0].equals("other")) {
			checkInputLength(4, userArgs.length);
			customerServiceManager.externalAccountTransfer(activeCustomer, 0, userArgs[1],
					Integer.parseInt(userArgs[2]), Integer.parseInt(userArgs[3]));
		} else if (userArgs[0].equals("pending")) {
			checkInputLength(1, userArgs.length);
			List<Transfer> transfers = customerServiceManager.viewPendingTransfers(activeCustomer);
			for (Transfer t : transfers) {
				System.out.println(t);
			}
		} else if (userArgs[0].equals("approve")) {
			checkInputLength(2, userArgs.length);
			customerServiceManager.acceptTransfer(activeCustomer, Integer.parseInt(userArgs[1]));
		} else if (userArgs[0].equals("decline")) {
			checkInputLength(2, userArgs.length);
			customerServiceManager.declineTransfer(activeCustomer, Integer.parseInt(userArgs[1]));
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

	private static void showEmployeeInputMenu()
			throws InvalidArgumentLengthException, NumberFormatException, UnexpectedAccountStateException {
		System.out.println("EMP");
		activeEmployee = (Employee) activeUser;
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		if (0 == userArgs.length) {
			throw new InvalidArgumentLengthException(1, 0);
		}
		checkInputLength(1, userArgs.length);
		if (userArgs[0].equals("logout")) {
			logout();
		} else if (userArgs[0].equals("q")) {
			quit();
		} else if (userArgs[0].equals("newcust")) {
			registerNewCustomerAccount(userArgs);
		}
		////////////////////////////////////// EMP specific actions//////////////
		else if (userArgs[0].equals("viewpendingaccs")) {
			checkInputLength(2, userArgs.length);
			for (Account a : employeeServiceManager.viewPendingAccountsForCustomer(userArgs[2])) {
				System.out.println(a);
			}
		} else if (userArgs[0].equals("viewpendingtransfers")) {
			checkInputLength(2, userArgs.length);
			for (Transfer t : employeeServiceManager.viewPendingTransfersForCustomer(userArgs[2])) {
				System.out.println(t);
			}
		} else if (userArgs[0].equals("viewcust")) {
			checkInputLength(2, userArgs.length);
			System.out.println(employeeServiceManager.viewCustomer(userArgs[2]));
		} else if (userArgs[0].equals("viewlogs")) {
			checkInputLength(1, userArgs.length);
			for (Displayable d : employeeServiceManager.viewTransactionLogs()) {
				System.out.println(d);
			}
		} else if (userArgs[0].equals("approveaccount")) {
			checkInputLength(3, userArgs.length);
			employeeServiceManager.approveAccount(userArgs[2], Integer.parseInt(userArgs[2]));
			System.out.println("Account approved");
		} else if (userArgs[0].equals("declineaccount")) {
			checkInputLength(3, userArgs.length);
			employeeServiceManager.declineAccount(userArgs[2], Integer.parseInt(userArgs[2]));
			System.out.println("Account declined");
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

	private static void registerNewCustomerAccount(String[] userArgs) throws InvalidArgumentLengthException {
		checkInputLength(3, userArgs.length);
		customerServiceManager.registerNewCustomerAccount(userArgs[1], userArgs[2]);
	}

	// this just throws errors
	private static void checkInputLength(int expected, int got) throws InvalidArgumentLengthException {
		if (expected != got) {
			throw new InvalidArgumentLengthException(expected, got);
		}
	}
}
