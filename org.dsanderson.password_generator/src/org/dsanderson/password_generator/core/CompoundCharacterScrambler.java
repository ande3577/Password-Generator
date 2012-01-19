package org.dsanderson.password_generator.core;

import java.util.ArrayList;
import java.util.Random;

public class CompoundCharacterScrambler implements ICharacterScrambler {
	ArrayList<ICharacterScrambler> characterScramblers;

	public CompoundCharacterScrambler(boolean upperCaseEnabled,
			boolean lowerCaseEnabled, boolean numericEnabled,
			boolean specialEnabled, Random random) {
		characterScramblers = new ArrayList<ICharacterScrambler>();

		characterScramblers.add(new DefaultCharacterScrambler(upperCaseEnabled,
				lowerCaseEnabled, numericEnabled, "", specialEnabled, "",
				random));

	}

	@Override
	public char scrambleCharacter(char ch) {
		char returnChar = '\0';
		for (int i = 0; i < characterScramblers.size() && returnChar == '\0'; i++) {
			returnChar = characterScramblers.get(i).scrambleCharacter(ch);
		}
		return returnChar;
	}

}
