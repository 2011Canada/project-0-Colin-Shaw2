package com.revature.repositories.postgres;

import com.revature.enums.AccountState;
import com.revature.enums.TransferState;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transfer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.TransferDAO;
import com.revature.repositories.UserDAO;

public class PostgressDebugger {
	public static void main(String[] args) {
		UserDAO ud = new UserPostgresDAO();
		EmployeeDAO ed = new EmployeePostgresDAO();
		CustomerDAO cd = new CustomerPostgresDAO();
		AccountDAO ad  = new AccountPostgresDAO();
		TransferDAO td  = new TransferPostgressDAO();
		
		try {
			Customer testCust = new Customer("test", "t");
			Employee testEmp = new Employee("testE", "E");
			Account testAcc = new Account(100L, 17, "kyle", AccountState.PENDING);
			Customer k = cd.findCustomerByName("kyle");
			Customer e = cd.findCustomerByName("erica");
			
			Transfer testTransfer = new Transfer(35, k, k.getAccounts().get(0).getAccountID(),
					e, e.getAccounts().get(0).getAccountID(), 56, TransferState.PENDING);

			System.out.println(td.addTransfer(testTransfer));
			testTransfer.declineTransfer();
//			System.out.println(td.updateTransferByID(testTransfer, testTransfer.getTransferId()));
//			System.out.println(td.findAllPendingTransfersForCustomer("kyle"));

			System.out.println(td.findTransferByID(4));
			System.out.println(td.findTransferByID(testTransfer.getTransferId()));
//			ad.addAccount(cd.findCustomerByName("kyle"), testAcc);
//			System.out.println(ad.findAllAccountsFromCustomerName("erica"));
//			System.out.println(ad.findAccountByCustomerandID(cd.findCustomerByName("erica"), 6));
//			System.out.println(ad.updateAccountByCustomerandID(cd.findCustomerByName("kyle"), testAcc.getAccountID(), testAcc));
//			System.out.println(ud.findUserByName("colin", true));
			
//			System.out.println(ad.findAllAccountsFromCustomerName("erica"));
			
//			System.out.println(cd.addCustomer(testCust));
//			System.out.println(ud.findUserByName(testCust.getUsername(), false));

//			System.out.println(ed.addEmployee(testEmp));
//			System.out.println(ed.findEmployeeByName("colin").getUsername());
//			System.out.println(cd.updateCustomer(new Customer("kyle", "alsdjkfhaosifufop")).getUsername());
//			System.out.println(cd.updateCustomer(new Customer("colisn", "alsdjkfhaosifufop")).getUsername());

//			System.out.println(ud.findUserByName("colin", true).getUsername());
//			System.out.println(cd.findCustomerByName("erica").getAccounts());
//			System.out.println(ud.findUserByName("kyle", false).getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
