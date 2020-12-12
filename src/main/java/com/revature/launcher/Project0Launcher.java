package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.DisplayController;

public class Project0Launcher {

	static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");
	
	//TODO addLogging
	//TODO double check we are throwing errors
	//TODO makeDataBase
	//TODO SQLdaos
	//TODO consider re adding IDs
	//TODO tests on service layer
	//TODO remove all TODO
	
	public static void main(String[] args) {
		eventLogger.info("TransactionLogger is working");
		while(true) {
			DisplayController.diplayMenu();
		}
	}
}
