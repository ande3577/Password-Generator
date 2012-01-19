package org.dsanderson.password_generator.core;

public class RandomCharacterGenerator implements IRandomCharacterGenerator {
	final String characters;
	final int weighting;
	final boolean canBeFirst;
	Boolean found;

	public RandomCharacterGenerator(String characters, int weighting,
			boolean canBeFirst) {
		this.characters = characters;
		this.weighting = weighting;
		this.canBeFirst = canBeFirst;
		found = false;
	}

	public RandomCharacterGenerator(char startingCharacter,
			char endingCharacter, int weighting, boolean canBeFirst) {
		String str = "";
		for (int index = (int) startingCharacter; index < (int) endingCharacter; index++) {
			str += (char) index;
		}
		this.characters = str;
		this.weighting = weighting;
		this.canBeFirst = canBeFirst;
		found = false;
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (randomData.randomNumber < NumberOfCharacters(Index)) {
			found = true;
			randomData.found = true;
			randomData.randomString += characters
					.charAt(randomData.randomNumber / weighting);
		} else {
			randomData.randomNumber -= NumberOfCharacters(Index);
		}
	}

	public int NumberOfCharacters(int Index) {
		if (Index == 0 && !canBeFirst)
			return 0;
		else
			return characters.length() * weighting;
	}

	public boolean Found() {
		return found;
	}

	public int Weighting() {
		return weighting;
	}

	public int RequiredLength() {
		return 1;
	}

}
