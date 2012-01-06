package org.dsanderson.password_generator.core;

public class RandomCharacterGenerator implements IRandomCharacterGenerator {
	final int length;
	final char startingCharacter;
	final int weighting;

	public RandomCharacterGenerator(char StartingCharacter,
			char EndingCharacter, int Weighting) {
		length = EndingCharacter - StartingCharacter + 1;
		startingCharacter = StartingCharacter;
		weighting = Weighting;
	}

	public RandomData ConvertToRandomCharacter(int RandomNumber) {
		RandomData randomData = new RandomData(RandomNumber);

		if (RandomNumber < NumberOfCharacters()) {
			randomData.found = true;
			randomData.character = (char) (RandomNumber / weighting + (int) startingCharacter);
		} else {
			randomData.randomNumber -= NumberOfCharacters();
		}
		return randomData;
	}

	public int NumberOfCharacters() {
		return length * weighting;
	}
}
