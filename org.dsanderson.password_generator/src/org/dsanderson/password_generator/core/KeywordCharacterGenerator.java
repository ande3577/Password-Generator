package org.dsanderson.password_generator.core;

import java.util.Random;

public class KeywordCharacterGenerator implements IRandomCharacterGenerator {
	private final String keyword;
	private final int startingIndex;
	private boolean found;

	public KeywordCharacterGenerator(String keyword, int passwordLength,
			Random randomGenerator) {
		this.keyword = keyword;
		startingIndex = randomGenerator.nextInt(passwordLength
				- keyword.length());
		found = false;
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (Index == startingIndex) {
			randomData.randomString += keyword;
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
