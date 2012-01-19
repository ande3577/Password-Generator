package org.dsanderson.password_generator.core;

public class KeywordCharacterGenerator implements IRandomCharacterGenerator {
	private final int startingIndex;
	private boolean found;;

	public KeywordCharacterGenerator() {
		UserSettings settings = UserSettings.getInstance();
		startingIndex = settings.random
				.nextInt(UserSettings.getInstance().passwordLength
						- settings.keyword.length() + 1);
		found = false;
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (Index == startingIndex) {
			String keyword = UserSettings.getInstance().keyword;

			for (int i = 0; i < keyword.length(); i++) {

				CompoundCharacterScrambler scrambler = new CompoundCharacterScrambler(
						keyword.charAt(i));
				randomData.randomNumber = UserSettings.getInstance().random
						.nextInt(scrambler.NumberOfCharacters(Index));
				scrambler.ConvertToRandomCharacter(randomData, Index);
			}
			randomData.found = true;
			found = true;
		}
	}

	public int NumberOfCharacters(int Index) {
		return 0;
	}

	public boolean Found(String password) {
		return found;
	}

	public int Weighting() {
		return 1;
	}

	public int RequiredLength() {
		UserSettings settings = UserSettings.getInstance();
		int requiredLength = settings.keyword.length();
		if (settings.upperCaseEnabled && requiredLength > 0)
			requiredLength--;
		if (settings.lowerCaseEnabled && requiredLength > 0)
			requiredLength--;
		return requiredLength;
	}

}
