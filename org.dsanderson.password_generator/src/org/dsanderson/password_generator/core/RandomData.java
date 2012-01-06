package org.dsanderson.password_generator.core;

public class RandomData {
	Boolean found;
	int randomNumber;
	char character;

	RandomData(int RandomNumber) {
		randomNumber = RandomNumber;
		found = false;
		character = '\0';
	}
}
