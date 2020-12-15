package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menus.DisplayController;

public class Project0Launcher {

	static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");
	
	//make cents
	//TODO tests on service layer
	//TODO remove all TODO
	//TODO make menu give more info
	
	public static void main(String[] args) {
		eventLogger.info("Program started");
		while(true) {
			DisplayController.diplayMenu();
		}
	}
}
