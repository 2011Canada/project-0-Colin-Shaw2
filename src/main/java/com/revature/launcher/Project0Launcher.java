package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.ConsoleController;

public class Project0Launcher {
	
	static Logger project0Logger = LogManager.getLogger("com.revature.e720");

	public static void main(String[] args) {
		project0Logger.info("Server has Started");
		ConsoleController.displayWelcome();
		ConsoleController.manageLoginInput();
		ConsoleController.manageLoggedInInput();
	}

}
