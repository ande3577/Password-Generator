package org.dsanderson.password_generator;

import org.dsanderson.password_generator.core.*;

public class ConsoleApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UserSettings settings = UserSettings.getInstance();

		for (int i = 0; i < args.length; i++) {
			String parameterName[] = args[i].split("=");

			if (parameterName[0].matches("--length")) {
				settings.passwordLength = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--uppercase")) {
				settings.upperCaseEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--uppercaseweight")) {
				settings.upperCaseWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--lowercase")) {
				settings.lowerCaseEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--lowercaseweight")) {
				settings.lowerCaseWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--number")) {
				settings.numericEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--numberweight")) {
				settings.numericWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--special")) {
				settings.specialEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--specialweight")) {
				settings.specialWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--keyword")) {
				settings.keyword = parameterName[1];
			}
		}

		PasswordGenerator generator = new PasswordGenerator();

		System.out.println(generator.GeneratePassword());
		System.exit(0);
	}
}
