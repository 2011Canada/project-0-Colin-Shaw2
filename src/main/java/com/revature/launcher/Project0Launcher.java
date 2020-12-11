package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.DisplayController;

public class Project0Launcher {
	
	static Logger project0Logger = LogManager.getLogger("com.revature.project0Colin");

	//TODO addLogging
	public static void main(String[] args) {
		project0Logger.info("Server has Started");
		while(true) {
			DisplayController.diplayMenu();
		}
	}
}
