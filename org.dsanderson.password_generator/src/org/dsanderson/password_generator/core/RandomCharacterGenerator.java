package org.dsanderson.password_generator.core;

public class RandomCharacterGenerator implements IRandomCharacterGenerator {
	final int length;
	final char startingCharacter;
	final int weighting;
	final boolean canBeFirst;
	Boolean found;

	public RandomCharacterGenerator(char startingCharacter,
			char endingCharacter, int weighting, boolean canBeFirst) {
		length = endingCharacter - startingCharacter + 1;
		this.startingCharacter = startingCharacter;
		this.weighting = weighting;
		this.canBeFirst = canBeFirst;
		found = false;
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (randomData.randomNumber < NumberOfCharacters(Index)) {
			found = true;
			randomData.found = true;
			randomData.randomString += (char) (randomData.randomNumber
					/ weighting + (int) startingCharacter);
		} else {
			randomData.randomNumber -= NumberOfCharacters(Index);
		}
	}

	public int NumberOfCharacters(int Index) {
		if (Index == 0 && !canBeFirst)
			return 0;
		else
			return length * weighting;
	}

	public boolean Found() {
		return found;
	}

	public int Weighting() {
		return weighting;
	}

}
