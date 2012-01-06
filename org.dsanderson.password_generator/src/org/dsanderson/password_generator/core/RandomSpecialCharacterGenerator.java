package org.dsanderson.password_generator.core;

public class RandomSpecialCharacterGenerator implements
		IRandomCharacterGenerator {
	public int weighting;

	private char STARTING_CHARACTERS[] = { '!', ':', '[', '{' };
	private char ENDING_CHARACTERS[] = { '/', '@', '`', '~' };

	private RandomCharacterGenerator specialCharacterGenerators[] = new RandomCharacterGenerator[STARTING_CHARACTERS.length];

	public RandomSpecialCharacterGenerator(int Weighting) {
		weighting = Weighting;

		for (int i = 0; i < STARTING_CHARACTERS.length; i++) {
			specialCharacterGenerators[i] = new RandomCharacterGenerator(
					STARTING_CHARACTERS[i], ENDING_CHARACTERS[i], Weighting);
		}
	}

	public RandomData ConvertToRandomCharacter(int RandomNumber) {
		RandomData randomData = new RandomData(RandomNumber);

		for (int i = 0; i < specialCharacterGenerators.length
				&& !randomData.found; i++) {
			randomData = specialCharacterGenerators[i]
					.ConvertToRandomCharacter(randomData.randomNumber);
		}

		return randomData;
	}

	public int NumberOfCharacters() {
		int numberOfCharacters = 0;
		for (int i = 0; i < specialCharacterGenerators.length; i++) {
			numberOfCharacters += specialCharacterGenerators[i]
					.NumberOfCharacters();
		}
		return numberOfCharacters;
	}
}
