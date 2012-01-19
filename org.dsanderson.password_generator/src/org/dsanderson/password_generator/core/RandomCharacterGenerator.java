package org.dsanderson.password_generator.core;

public class RandomCharacterGenerator implements IRandomCharacterGenerator {
	final String characters;
	final int weighting;
	final boolean canBeFirst;

	public RandomCharacterGenerator(String characters, int weighting,
			boolean canBeFirst) {
		this.characters = characters;
		this.weighting = weighting;
		this.canBeFirst = canBeFirst;
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
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (randomData.randomNumber < NumberOfCharacters(Index)) {
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

	public boolean Found(String password) {
		for (int index = 0; index < characters.length(); index++) {
			if (password.contains(Character.toString(characters.charAt(index))))
				return true;
		}
		return false;
	}

	public int Weighting() {
		return weighting;
	}

	public int RequiredLength() {
		return 1;
	}

}
