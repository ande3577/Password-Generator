package org.dsanderson.password_generator.core;

import java.util.Random;

public class KeywordCharacterGenerator implements IRandomCharacterGenerator {
	private final String keyword;
	private final int startingIndex;
	private boolean found;
	private CompoundCharacterScrambler scrambler;

	public KeywordCharacterGenerator(String keyword, int passwordLength,
			boolean upperCaseEnabled, boolean lowerCaseEnabled,
			boolean numericEnabled, boolean specialEnabled,
			Random randomGenerator) {
		this.keyword = keyword;
		startingIndex = randomGenerator.nextInt(passwordLength
				- keyword.length() + 1);
		found = false;
		scrambler = new CompoundCharacterScrambler(upperCaseEnabled,
				lowerCaseEnabled, numericEnabled, specialEnabled,
				randomGenerator);
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (Index == startingIndex) {
			String keywordString = "";

			for (int index = 0; index < keyword.length(); index++) {
				char scrambledCh = scrambler.scrambleCharacter(keyword
						.charAt(index));
				if (scrambledCh == '\0') {
					keywordString = keyword;
					break;
				} else {
					keywordString += scrambledCh;
				}
			}

			randomData.randomString += keywordString;
			randomData.found = true;
			found = true;
		}
	}

	public int NumberOfCharacters(int Index) {
		return 0;
	}

	public boolean Found() {
		return found;
	}

	public int Weighting() {
		return 1;
	}

	public int RequiredLength() {
		return keyword.length();
	}

}
