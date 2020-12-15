package com.revature.menus;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.enums.MenuState;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.InvalidArgumentLengthException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UnexpectedAccountStateException;
import com.revature.exceptions.UnexpectedTransferStateException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.TransferDAO;
import com.revature.repositories.UserDAO;
import com.revature.repositories.postgres.AccountPostgresDAO;
import com.revature.repositories.postgres.CustomerPostgresDAO;
import com.revature.repositories.postgres.EmployeePostgresDAO;
import com.revature.repositories.postgres.TransferPostgressDAO;
import com.revature.repositories.postgres.UserPostgresDAO;
import com.revature.services.CustomerServiceController;
import com.revature.services.CustomerServiceInterface;
import com.revature.services.EmployeeServiceController;
import com.revature.services.EmployeeServiceInterface;
import com.revature.services.UserServiceController;
import com.revature.services.UserServiceInterface;

public class DisplayController {

	static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");
	static MenuState menuState = MenuState.NOT_LOGGED_IN;
	static User activeUser;
	static Customer activeCustomer;
	static Employee activeEmployee;

	private static CustomerDAO custDAO = new CustomerPostgresDAO();
	private static EmployeeDAO empDAO = new EmployeePostgresDAO();
	private static AccountDAO accDAO = new AccountPostgresDAO();
	private static TransferDAO transDAO = new TransferPostgressDAO();
	
	static UserServiceInterface userServiceManager = new UserServiceController(custDAO, empDAO);
	static CustomerServiceInterface customerServiceManager = new CustomerServiceController(accDAO, custDAO, transDAO);
	static EmployeeServiceInterface employeeServiceManager = new EmployeeServiceController(custDAO, accDAO, transDAO);
	
	
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
			logException(e);
			System.out.println(e.getMessage());
		} catch (UserNotFoundException e) {
			logException(e);
			System.out.println(e.getMessage());
		} catch (TransferNotFoundException e) {
			logException(e);
			System.out.println(e.getMessage());
		}catch (AccountNotFoundException e) {
			logException(e);
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			logException(e);
			System.out.print("Ooops we expected an integer ");
			System.out.println(e.getMessage());
		} catch (UnexpectedAccountStateException e) {
			logException(e);
			System.out.println(e.getMessage());
		} catch (UnexpectedTransferStateException e) {
			logException(e);
			System.out.println(e.getMessage());
		} catch (NegativeBalanceException e) {
			logException(e);
			System.out.println(e.getMessage());
		}catch (SQLException e) {
			logException(e);
			System.out.println(e.getMessage());
		}
		System.out.println("Press Enter to continue");
		userInputScanner.nextLine();
	}

	private static void showNotLoggedInMenu() throws UserNotFoundException, SQLException, AccountNotFoundException {
		System.out.println("Welcome To Colin's Banking App");

		System.out.println("Are you an Employee (y or n)"); // Output user input

		Boolean isEmployee = (userInputScanner.nextLine().equals("y")?true:false); // Read user input

		System.out.println("Please enter a username"); // Output user input

		String username = userInputScanner.nextLine(); // Read user input
		System.out.println("Username is: " + username); // Output user input

		System.out.println("Please enter a password");
		String pwd = userInputScanner.nextLine(); // Read user input

		User temp = userServiceManager.login(username, pwd, isEmployee);

		activeUser = temp;
		System.out.println("Welcome " + activeUser.getUsername());
		if (activeUser instanceof Customer) {
			menuState = MenuState.MAIN_CUSTOMER_MENU;
		} else if (activeUser instanceof Employee) {
			menuState = MenuState.MAIN_EMPLOYEE_MENU;
		}
	}

	private static void showCustomerInputMenu()
			throws InvalidArgumentLengthException, NumberFormatException, UnexpectedTransferStateException,
			NegativeBalanceException, UserNotFoundException, AccountNotFoundException, TransferNotFoundException, SQLException {
		System.out.println("Please enter a menu option or q to quit");
		System.out.println("logout");
		System.out.println("newcust {username} {password}");
		System.out.println("viewaccs");
		System.out.println("newacc {startingbalance}");
		System.out.println("getbal {accountnumber}");
		System.out.println("withdraw {accountnumber} {amount}");
		System.out.println("deposit {accountnumber} {amount}");
		System.out.println("transfer !This will bring you to the transfer menu!");
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
			////////////////////////////////////// CUST specific actions//////////////
		} else if (userArgs[0].equals("viewaccs")) {
			checkInputLength(1, userArgs.length);
			List<Account> accounts = customerServiceManager.viewAccounts(activeCustomer);
			for (Account a : accounts) {
				System.out.println(a);
			}
		} else if (userArgs[0].equals("newacc")) {
			checkInputLength(2, userArgs.length);
			System.out.println("New Account made "
					+ customerServiceManager.applyForBankAccount(activeCustomer, Integer.parseInt(userArgs[1]))
					+ " starting balance is " + Integer.parseInt(userArgs[1]) +"\nAccount is pending until approved by an employee");

		} else if (userArgs[0].equals("getbal")) {
			checkInputLength(2, userArgs.length);
			System.out.println("Current ballance for account " + userArgs[1] + " is "
					+ +customerServiceManager.viewBalance(activeCustomer, Integer.parseInt(userArgs[1])));
		} else if (userArgs[0].equals("withdraw")) {
			checkInputLength(3, userArgs.length);
			Account a = customerServiceManager.withdraw(activeCustomer, Integer.parseInt(userArgs[1]),
					Integer.parseInt(userArgs[2]));
			System.out.println("Withdrawing " + userArgs[2] + "$ from " + a);
		} else if (userArgs[0].equals("deposit")) {
			checkInputLength(3, userArgs.length);
			Account a = customerServiceManager.deposit(activeCustomer, Integer.parseInt(userArgs[1]),
					Integer.parseInt(userArgs[2]));
			System.out.println("Depositting " + userArgs[2] + "$ from " + a);
		} else if (userArgs[0].equals("transfer")) {
			checkInputLength(1, userArgs.length);
			menuState = MenuState.CUSTOMER_TRANSFER_MENU;
		} else {
			System.out.println("Invalid option");
		}
	}

	private static void showCustomerTransfersMenu()
			throws InvalidArgumentLengthException, NumberFormatException, UnexpectedTransferStateException,
			NegativeBalanceException, AccountNotFoundException, UserNotFoundException, TransferNotFoundException, SQLException {
		System.out.println("Please enter a transfer option or q to quit");
		System.out.println("logout");
		System.out.println("selftrans {fromAccountID} {toAccountID} {amount}");
		System.out.println("externaltrans {fromAccountID} {toCustomerName} {toAccountID} {amount}");
		System.out.println("viewpending");
		System.out.println("approve {transferID}");
		System.out.println("decline {transferID}");
		System.out.println("back");
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		if (0 == userArgs.length) {
			throw new InvalidArgumentLengthException(1, 0);
		}
		if (userArgs[0].equals("selftrans")) {
			checkInputLength(4, userArgs.length);
			List<Account> accs = customerServiceManager.internalAccountTransfer(activeCustomer,
					Integer.parseInt(userArgs[1]), Integer.parseInt(userArgs[2]), Integer.parseInt(userArgs[3]));
			System.out.println(accs.get(0));
			System.out.println(accs.get(1));
		} else if (userArgs[0].equals("externaltrans")) {
			checkInputLength(5, userArgs.length);
			System.out.println("Transfer created" +
					customerServiceManager.externalAccountTransfer(activeCustomer, Integer.parseInt(userArgs[1]),
							userArgs[2], Integer.parseInt(userArgs[3]), Integer.parseInt(userArgs[4])));
		} else if (userArgs[0].equals("viewpending")) {
			checkInputLength(1, userArgs.length);
			List<Transfer> transfers = customerServiceManager.viewPendingTransfers(activeCustomer);
			for (Transfer t : transfers) {
				System.out.println(t);
			}
		} else if (userArgs[0].equals("approve")) {
			//TODO check this again
			checkInputLength(2, userArgs.length);
			System.out.println(
			customerServiceManager.acceptTransfer(activeCustomer, Integer.parseInt(userArgs[1])));
		} else if (userArgs[0].equals("decline")) {
			//TODO check this again
			checkInputLength(2, userArgs.length);
			System.out.println(
			customerServiceManager.declineTransfer(activeCustomer, Integer.parseInt(userArgs[1])));
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

	//TODO make this menu better
	private static void showEmployeeInputMenu() throws InvalidArgumentLengthException, NumberFormatException,
			UnexpectedAccountStateException, AccountNotFoundException, UserNotFoundException, TransferNotFoundException, SQLException {
		System.out.println("Please enter a menu option or q to quit");
		System.out.println("logout");
		System.out.println("newcust {username} {password}");
		System.out.println("viewpendingaccs {customerName}");
		System.out.println("viewpendingtransfers {customerName}");
		System.out.println("viewcust {customerName}");
		System.out.println("viewlogs");
		System.out.println("approveaccount {customerName} {accountID}");
		System.out.println("declineaccount {customerName} {accountID}");
		activeEmployee = (Employee) activeUser;
		String[] userArgs = userInputScanner.nextLine().split(" "); // Read user input

		if (0 == userArgs.length) {
			throw new InvalidArgumentLengthException(1, userArgs.length);
		}
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
			for (Account a : employeeServiceManager.viewPendingAccountsForCustomer(userArgs[1])) {
				System.out.println(a);
			}
		} else if (userArgs[0].equals("viewpendingtransfers")) {
			checkInputLength(2, userArgs.length);
			for (Transfer t : employeeServiceManager.viewPendingTransfersForCustomer(userArgs[1])) {
				System.out.println(t);
			}
		} else if (userArgs[0].equals("viewcust")) {
			checkInputLength(2, userArgs.length);
			Customer temp = employeeServiceManager.viewCustomer(userArgs[1]);
			System.out.println(temp);
			for(Account a :temp.getAccounts()) {
				System.out.println(a);
			}
		} else if (userArgs[0].equals("viewlogs")) {
			checkInputLength(1, userArgs.length);
			for (String d : employeeServiceManager.viewTransactionLogs()) {
				System.out.println(d);
			}
		} else if (userArgs[0].equals("approveaccount")) {
			checkInputLength(3, userArgs.length);
			employeeServiceManager.approveAccount(userArgs[1], Integer.parseInt(userArgs[2]));
			System.out.println("Account approved");
		} else if (userArgs[0].equals("declineaccount")) {
			checkInputLength(3, userArgs.length);
			employeeServiceManager.declineAccount(userArgs[1], Integer.parseInt(userArgs[2]));
			System.out.println("Account declined");
		} else {
			System.out.println("Invalid option");
		}
		

	}

	// this is a helper function to reduce code being rewritten
	private static void logout() {
		userServiceManager.logout(activeUser.getUsername());
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

	private static void registerNewCustomerAccount(String[] userArgs) throws InvalidArgumentLengthException, AccountNotFoundException, TransferNotFoundException, UserNotFoundException, SQLException {
		checkInputLength(3, userArgs.length);
		System.out.println(
				"New account created " + userServiceManager.registerNewCustomerAccount(userArgs[1], userArgs[2]));
	}

	// this just throws errors
	private static void checkInputLength(int expected, int got) throws InvalidArgumentLengthException {
		if (expected != got) {
			throw new InvalidArgumentLengthException(expected, got);
		}
	}

	private static void logException(Exception e) {
		if (activeUser == null) {
			eventLogger.info("Current User" + " " + activeUser + " " + e.getMessage());
		} else {
			eventLogger.info("Current User" + " " + activeUser.getUsername() + " " + e.getMessage());
		}
	}
}
