/**
 * @author David S Anderson
 * 
 * Copyright (C) 2012
 */

package org.dsanderson.password_generator.core;

public class RandomData {
	Boolean found;
	int randomNumber;
	String randomString;

	RandomData(int RandomNumber) {
		randomNumber = RandomNumber;
		found = false;
		randomString = "";
	}
}
