/**
 * @author David S Anderson
 * 
 * Copyright (C) 2012
 */

package org.dsanderson.password_generator.core;

import java.util.Random;

public class UserSettings {
	static UserSettings instancePtr = null;

	public boolean upperCaseEnabled = true, lowerCaseEnabled = true,
			numericEnabled = true, specialEnabled = false;
	public int upperCaseWeight = 1, lowerCaseWeight = 1, numericWeight = 1,
			specialWeight = 1;
	public int passwordLength = 10;
	public Random random = new Random();
	public String keyword = "";

	private UserSettings() {

	}

	static public UserSettings getInstance() {
		if (instancePtr == null)
			instancePtr = new UserSettings();

		return instancePtr;
	}

}
