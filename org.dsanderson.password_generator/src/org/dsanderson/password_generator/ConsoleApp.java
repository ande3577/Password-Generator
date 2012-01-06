package org.dsanderson.password_generator;

import org.dsanderson.password_generator.core.*;

public class ConsoleApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int length = 12;
		Boolean upperCaseEnabled = true;
		int upperCaseWeight = 1;
		Boolean lowerCaseEnabled = true;
		int lowerCaseWeight = 1;
		Boolean numbersEnabled = true;
		int numbersWeight = 1;
		Boolean specialCharactersEnabled = true;
		int specialCharactersWeight = 1;

		for (int i = 0; i < args.length; i++) {
			String parameterName[] = args[i].split("=");

			if (parameterName[0].matches("--length")) {
				length = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--uppercase")) {
				upperCaseEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--uppercaseweight")) {
				upperCaseWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--lowercase")) {
				lowerCaseEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--lowercaseweight")) {
				lowerCaseWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--number")) {
				numbersEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--numberweight")) {
				numbersWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--special")) {
				specialCharactersEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--specialweight")) {
				specialCharactersWeight = Integer.parseInt(parameterName[1]);
			}
		}

		PasswordGenerator generator = new PasswordGenerator(length,
				upperCaseEnabled, upperCaseWeight, lowerCaseEnabled,
				lowerCaseWeight, numbersEnabled, numbersWeight,
				specialCharactersEnabled, specialCharactersWeight);

		System.out.print(generator.GeneratePassword());
		System.exit(0);
	}
}
