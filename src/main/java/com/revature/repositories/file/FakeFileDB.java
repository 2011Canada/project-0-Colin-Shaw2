package com.revature.repositories.file;

import java.util.HashMap;

import com.revature.enums.TransferState;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transfer;
import com.revature.models.User;

public class FakeFileDB {

	static HashMap<String, User> db = new HashMap<>();
	static HashMap<Integer, Transfer> transfer = new HashMap<>();
	
	static {
		Customer cust1 = new Customer("kyle", "g");
		Customer cust2 = new Customer("erica", "s");
		Employee emp1 = new Employee("kurt", "g");
		Employee emp2 = new Employee("colin", "s");
		db.put(cust1.getUsername(), cust1);
		db.put(cust2.getUsername(), cust2);
		db.put(emp1.getUsername(), emp1);
		db.put(emp2.getUsername(), emp2);

//		cust1.addAccount(new Account(100,0, cust1.getUsername()));
//		cust1.addAccount(new Account(200,1, cust1.getUsername()));
//		
//		cust2.addAccount(new Account(1000,0, cust2.getUsername()));
//		cust2.addAccount(new Account(2000,1, cust2.getUsername()));
		
		transfer.put(0, new Transfer(6, (Customer)db.get("kyle"), 0, (Customer)db.get("erica"), 1, 0, TransferState.PENDING));
		transfer.put(0, new Transfer(11, (Customer)db.get("erica"), 0, (Customer)db.get("kyle"), 1, 0, TransferState.PENDING));
		

	}
}
