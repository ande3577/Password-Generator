/**
 * 
 */
package org.dsanderson.password_generator.core;

import java.util.Random;

/**
 * @author dsanderson
 * 
 */
public class DefaultCharacterScrambler implements ICharacterScrambler {
	private final boolean upperCaseEnabled, lowerCaseEnabled, numericEnabled,
			specialEnabled;
	private final String numericString;
	private final String specialCharacterString;
	private Random random;

	public DefaultCharacterScrambler(boolean upperCaseEnabled,
			boolean lowerCaseEnabled, boolean numericEnabled,
			String numericString, boolean specialEnabled,
			String specialCharacterString, Random random) {
		this.upperCaseEnabled = upperCaseEnabled;
		this.lowerCaseEnabled = lowerCaseEnabled;
		this.numericEnabled = numericEnabled;
		this.numericString = numericString;
		this.specialEnabled = specialEnabled;
		this.specialCharacterString = specialCharacterString;
		this.random = random;
	}

	@Override
	public char scrambleCharacter(char ch) {
		String characterString = "";

		if (lowerCaseEnabled)
			characterString += Character.toLowerCase(ch);
		
		if (upperCaseEnabled)
			characterString += Character.toUpperCase(ch);

		if (specialEnabled)
			characterString += specialCharacterString;

		if (numericEnabled)
			characterString += numericString;

		return selectRandomFromString(characterString);
	}

	private char selectRandomFromString(String str) {
		if (str.length() == 0)
			return '\0';

		int index = random.nextInt(str.length());
		return str.charAt(index);
	}

}
