package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;

public class UserServiceControllerTest {

	private CustomerDAO custDAO;
	private EmployeeDAO empDAO;
	private UserServiceInterface usController;
	private Customer testCustomer;
	private Employee testEmployee;

	@BeforeEach
	public void setup() {
		this.custDAO = mock(CustomerDAO.class);
		this.empDAO = mock(EmployeeDAO.class);
		this.usController = new UserServiceController(custDAO, empDAO);
		this.testCustomer = new Customer("testc", "c");
		this.testEmployee = new Employee("teste", "e");
		try {
			when(custDAO.findCustomerByName(null)).thenThrow(UserNotFoundException.class);
			when(custDAO.findCustomerByName("testc")).thenReturn(testCustomer);

			when(empDAO.findEmployeeByName(null)).thenThrow(UserNotFoundException.class);
			when(empDAO.findEmployeeByName("teste")).thenReturn(testEmployee);

			// when(custDAO.addCustomer(null)).thenThrow(UserNotFoundException.class);
			// when(custDAO.addCustomer(testCustomer)).thenReturn(testCustomer);

			when(custDAO.addCustomer(any(Customer.class))).then(returnsFirstArg());

		} catch (UserNotFoundException | AccountNotFoundException | SQLException | TransferNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void loginCustomerTestNullNull() {
		assertThrows(UserNotFoundException.class, () -> usController.login(null, null, false));
	}

	@Test
	public void loginCustomerTestCorrectNull() {
		assertThrows(UserNotFoundException.class, () -> usController.login("testc", null, false));
	}

	@Test
	public void loginCustomerTestCorrectIncorrect() {
		assertThrows(UserNotFoundException.class, () -> usController.login("testc", "asdf", false));

	}

	@Test
	public void loginCustomerTestCorrectCorrect() {
		try {
			assertEquals(true, testCustomer.equals(usController.login("testc", "c", false)));
		} catch (UserNotFoundException | SQLException | AccountNotFoundException e) {
			e.printStackTrace();
			Assertions.fail();
		}
	}

	@Test
	public void loginCustomerTestTryToLoginEmployee() {
		assertThrows(UserNotFoundException.class, () -> usController.login("teste", "e", false));
	}

	@Test
	public void loginEmployeeTestNullNull() {
		assertThrows(UserNotFoundException.class, () -> usController.login(null, null, true));
	}

	@Test
	public void loginEmployeeTestCorrectNull() {
		assertThrows(UserNotFoundException.class, () -> usController.login("teste", null, true));
	}

	@Test
	public void loginEmployeeTestCorrectIncorrect() {
		assertThrows(UserNotFoundException.class, () -> usController.login("teste", "asdf", true));
	}

	@Test
	public void loginEmployeeTestCorrectCorrect() {
		try {
			assertEquals(true, testEmployee.equals(usController.login("teste", "e", true)));
		} catch (UserNotFoundException | SQLException | AccountNotFoundException e) {
			e.printStackTrace();
			Assertions.fail();
		}
	}

	@Test
	public void loginEmployeeTestTryToLoginCustomer() {
		assertThrows(UserNotFoundException.class, () -> usController.login("testc", "c", true));
	}

	@Test
	public void logoutTest() {
		usController.logout("testc");
		usController.logout("teste");
		usController.logout(null);
	}

	@Test
	public void registerNewCustomerAccountTestNullNull() {
		assertThrows(UserNotFoundException.class, () -> usController.registerNewCustomerAccount(null, null));
	}

	@Test
	public void registerNewCustomerAccountTestNullExists() {
		assertThrows(UserNotFoundException.class, () -> usController.registerNewCustomerAccount(null, "c"));
	}

	@Test
	public void registerNewCustomerAccountTestExistsNull() {
		assertThrows(UserNotFoundException.class, () -> usController.registerNewCustomerAccount("testc", null));
	}

	@Test
	public void registerNewCustomerAccountTestNewNull() {
		assertThrows(UserNotFoundException.class, () -> usController.registerNewCustomerAccount("sdfe", null));
	}

	@Test
	public void registerNewCustomerAccountTestEmployeeAlreadyExists() {
		Customer custFromEmployee = new Customer(testEmployee.getUsername(), testEmployee.getPassword());
		try {
			assertEquals(true, custFromEmployee.equals(usController.registerNewCustomerAccount("teste", "e")));
		} catch (AccountNotFoundException | TransferNotFoundException | UserNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assertions.fail();
		}
	}

	@Test
	public void registerNewCustomerAccountTestCorrect() {
		Customer test = new Customer("teste", "e");
		try {
			assertEquals(true, test.equals(usController.registerNewCustomerAccount("teste", "e")));
		} catch (AccountNotFoundException | TransferNotFoundException | UserNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assertions.fail();
		}
	}
}
