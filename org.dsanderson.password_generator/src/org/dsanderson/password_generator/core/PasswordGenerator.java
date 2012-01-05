package org.dsanderson.password_generator.core;

import java.util.Random;

public class PasswordGenerator {

	private class RandomData {
		Boolean found;
		int randomNumber;
		char character;

		RandomData(int RandomNumber) {
			randomNumber = RandomNumber;
			found = false;
			character = '\0';
		}
	}

	private abstract class IRandomCharacterGenerator {
		abstract RandomData ConvertToRandomCharacter(int RandomNumber);

		abstract int NumberOfCharacters();
	}

	private class RandomCharacterGenerator extends IRandomCharacterGenerator {
		final int length;
		final char startingCharacter;
		final int weighting;

		public RandomCharacterGenerator(char StartingCharacter,
				char EndingCharacter, int Weighting) {
			length = EndingCharacter - StartingCharacter + 1;
			startingCharacter = StartingCharacter;
			weighting = Weighting;
		}

		RandomData ConvertToRandomCharacter(int RandomNumber) {
			RandomData randomData = new RandomData(RandomNumber);

			if (RandomNumber < NumberOfCharacters()) {
				randomData.found = true;
				randomData.character = (char) (RandomNumber / weighting + (int) startingCharacter);
			} else {
				randomData.randomNumber -= NumberOfCharacters();
			}
			return randomData;
		}

		int NumberOfCharacters() {
			return length * weighting;
		}
	}

	private class RandomSpecialCharacterGenerator extends
			IRandomCharacterGenerator {
		private char STARTING_CHARACTERS[] = { '!', ':', '[', '{' };
		private char ENDING_CHARACTERS[] = { '/', '@', '`', '~' };

		private RandomCharacterGenerator specialCharacterGenerators[] = new RandomCharacterGenerator[STARTING_CHARACTERS.length];

		public RandomSpecialCharacterGenerator(int Weighting) {
			for (int i = 0; i < STARTING_CHARACTERS.length; i++) {
				specialCharacterGenerators[i] = new RandomCharacterGenerator(
						STARTING_CHARACTERS[i], ENDING_CHARACTERS[i], Weighting);
			}
		}

		RandomData ConvertToRandomCharacter(int RandomNumber) {
			RandomData randomData = new RandomData(RandomNumber);

			for (int i = 0; i < specialCharacterGenerators.length
					&& !randomData.found; i++) {
				randomData = specialCharacterGenerators[i]
						.ConvertToRandomCharacter(randomData.randomNumber);
			}

			return randomData;
		}

		int NumberOfCharacters() {
			int numberOfCharacters = 0;
			for (int i = 0; i < specialCharacterGenerators.length; i++) {
				numberOfCharacters += specialCharacterGenerators[i]
						.NumberOfCharacters();
			}
			return numberOfCharacters;
		}
	}

	Integer length;
	Boolean upperCaseLetters;
	Boolean lowerCaseLetters;
	Boolean numbers;
	Boolean specialCharacters;
	Random randomGenerator;

	Boolean upperCaseOccurs;
	Boolean lowerCaseOccurs;
	Boolean numberOccurs;
	Boolean specialCharacterOccurs;

	private RandomCharacterGenerator upperCaseCharacterGenerator;
	private RandomCharacterGenerator lowerCaseCharacterGenerator;
	private RandomCharacterGenerator numericCharacterGenerator;
	private RandomSpecialCharacterGenerator specialCharacterGenerator;

	public PasswordGenerator(int Length, Boolean UpperCaseLetters,
			int UpperCaseWeight, Boolean LowerCaseLetters, int LowerCaseWeight,
			Boolean Numbers, int NumberWeight, Boolean SpecialCharacters,
			int SpecialCharacterWeight) {
		length = Length;
		upperCaseLetters = UpperCaseLetters;
		lowerCaseLetters = LowerCaseLetters;
		numbers = Numbers;
		specialCharacters = SpecialCharacters;
		randomGenerator = new Random();

		upperCaseCharacterGenerator = new RandomCharacterGenerator('A', 'Z',
				UpperCaseWeight);
		lowerCaseCharacterGenerator = new RandomCharacterGenerator('a', 'z',
				LowerCaseWeight);
		numericCharacterGenerator = new RandomCharacterGenerator('0', '9',
				NumberWeight);
		specialCharacterGenerator = new RandomSpecialCharacterGenerator(
				SpecialCharacterWeight);
	}

	private int DetermineCharacterCount(int Index) {
		int characterCount = 0;
		Boolean requireLetter = (Index == 0)
				&& (upperCaseLetters || lowerCaseLetters);

		if (upperCaseLetters)
			characterCount += upperCaseCharacterGenerator.NumberOfCharacters();
		if (lowerCaseLetters)
			characterCount += lowerCaseCharacterGenerator.NumberOfCharacters();
		if (numbers && !requireLetter)
			characterCount += numericCharacterGenerator.NumberOfCharacters();
		if (specialCharacters && !requireLetter)
			characterCount += specialCharacterGenerator.NumberOfCharacters();

		return characterCount;
	}

	private RandomData GenerateCharacter(int Index) {
		RandomData randomData = new RandomData(
				randomGenerator.nextInt(DetermineCharacterCount(Index)));
		if (upperCaseLetters) {
			randomData = upperCaseCharacterGenerator
					.ConvertToRandomCharacter(randomData.randomNumber);
			if (randomData.found)
				upperCaseOccurs = true;
		}
		if (!randomData.found && lowerCaseLetters) {
			randomData = lowerCaseCharacterGenerator
					.ConvertToRandomCharacter(randomData.randomNumber);
			if (randomData.found)
				lowerCaseOccurs = true;
		}
		if (!randomData.found && numbers) {
			randomData = numericCharacterGenerator
					.ConvertToRandomCharacter(randomData.randomNumber);
			if (randomData.found)
				numberOccurs = true;
		}
		if (!randomData.found && specialCharacters) {
			randomData = specialCharacterGenerator
					.ConvertToRandomCharacter(randomData.randomNumber);
			if (randomData.found)
				specialCharacterOccurs = true;
		}

		return randomData;
	}

	public String GeneratePassword() {
		int requiredLength = 0;
		if (upperCaseLetters)
			requiredLength++;
		else if (lowerCaseLetters)
			requiredLength++;
		else if (numbers)
			requiredLength++;
		else if (specialCharacters)
			requiredLength++;

		if (requiredLength > length)
			return String.format("error: length %d < required %d", length,
					requiredLength);

		String password;
		do {
			password = "";

			upperCaseOccurs = !upperCaseLetters;
			lowerCaseOccurs = !lowerCaseLetters;
			numberOccurs = !numbers;
			specialCharacterOccurs = !specialCharacters;

			for (int i = 0; i < length; i++) {
				RandomData randomData = GenerateCharacter(i);
				if (!randomData.found)
					return "Invalid random character";
				else
					password += randomData.character;
			}
		} while (!upperCaseOccurs || !lowerCaseOccurs || !numberOccurs
				|| !specialCharacterOccurs);
		return password;
	}
}
