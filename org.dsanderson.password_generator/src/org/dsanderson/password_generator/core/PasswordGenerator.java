package org.dsanderson.password_generator.core;

import java.util.Random;

public class PasswordGenerator {

	private Integer length;
	private Boolean upperCaseLetters;
	private Boolean lowerCaseLetters;
	private Boolean numbers;
	private Boolean specialCharacters;
	private Random randomGenerator;

	private Boolean upperCaseOccurs;
	private Boolean lowerCaseOccurs;
	private Boolean numberOccurs;
	private Boolean specialCharacterOccurs;
	
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
		{
			requiredLength++;
			if (upperCaseCharacterGenerator.weighting == 0)
				return "Error, cannot set weight to 0";
		}
		
		if (lowerCaseLetters)
		{
			requiredLength++;
			if (lowerCaseCharacterGenerator.weighting == 0)
				return "Error, cannot set weight to 0";
		}
		
		if (numbers)
		{
			requiredLength++;
			if (numericCharacterGenerator.weighting == 0)
				return "Error, cannot set weight to 0";
		}
		
		if (specialCharacters)
		{
			requiredLength++;
			if (specialCharacterGenerator.weighting == 0)
				return "Error, cannot set weight to 0";
		}

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
