package org.dsanderson.password_generator.core;

public class RandomSpecialCharacterGenerator implements
		IRandomCharacterGenerator {
	final int weighting;

	private char STARTING_CHARACTERS[] = { '!', ':', '[', '{' };
	private char ENDING_CHARACTERS[] = { '/', '@', '`', '~' };

	private RandomCharacterGenerator specialCharacterGenerators[] = new RandomCharacterGenerator[STARTING_CHARACTERS.length];

	public RandomSpecialCharacterGenerator(int Weighting, boolean canBeFirst) {
		weighting = Weighting;

		for (int i = 0; i < STARTING_CHARACTERS.length; i++) {
			specialCharacterGenerators[i] = new RandomCharacterGenerator(
					STARTING_CHARACTERS[i], ENDING_CHARACTERS[i], Weighting,
					canBeFirst);
		}
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		for (int i = 0; i < specialCharacterGenerators.length
				&& !randomData.found; i++) {
			specialCharacterGenerators[i].ConvertToRandomCharacter(randomData, Index);
		}
	}

	public int NumberOfCharacters(int Index) {
		int numberOfCharacters = 0;
		for (int i = 0; i < specialCharacterGenerators.length; i++) {
			numberOfCharacters += specialCharacterGenerators[i]
					.NumberOfCharacters(Index);
		}
		return numberOfCharacters;
	}

	public boolean Found() {
		for (int i = 0; i < specialCharacterGenerators.length; i++) {
			if (specialCharacterGenerators[i].Found())
				return true;
		}
		return false;
	}
	
	public int Weighting()
	{
		return weighting;
	}
	
}
