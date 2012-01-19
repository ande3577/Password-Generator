package org.dsanderson.password_generator.core;

import java.util.Random;
import java.util.ArrayList;

public class PasswordGenerator {

	private Integer length;
	private Random randomGenerator;

	private ArrayList<IRandomCharacterGenerator> characterGenerators;

	public PasswordGenerator(int Length, Boolean UpperCaseLetters,
			int UpperCaseWeight, Boolean LowerCaseLetters, int LowerCaseWeight,
			boolean Numbers, int NumberWeight, boolean SpecialCharacters,
			int SpecialCharacterWeight, String keyword) {
		length = Length;
		randomGenerator = new Random();
		characterGenerators = new ArrayList<IRandomCharacterGenerator>();

		if (keyword.length() > 0)
			characterGenerators.add(new KeywordCharacterGenerator(keyword,
					Length, UpperCaseLetters, LowerCaseLetters, Numbers,
					SpecialCharacters, randomGenerator));

		if (UpperCaseLetters)
			characterGenerators.add(new RandomCharacterGenerator('A', 'Z',
					UpperCaseWeight, true));

		if (LowerCaseLetters)
			characterGenerators.add(new RandomCharacterGenerator('a', 'z',
					LowerCaseWeight, true));

		if (Numbers)
			characterGenerators.add(new RandomCharacterGenerator('0', '9',
					NumberWeight, !(UpperCaseLetters || LowerCaseLetters)));

		if (SpecialCharacters)
			characterGenerators.add(new RandomSpecialCharacterGenerator(
					SpecialCharacterWeight,
					!(UpperCaseLetters || LowerCaseLetters)));
	}

	private int DetermineCharacterCount(int Index) {
		int characterCount = 0;

		for (int i = 0; i < characterGenerators.size(); i++) {
			characterCount += characterGenerators.get(i).NumberOfCharacters(
					Index);
		}

		return characterCount;
	}

	private void GenerateCharacter(RandomData randomData, int Index) {
		randomData.randomNumber = randomGenerator
				.nextInt(DetermineCharacterCount(Index));

		randomData.found = false;

		for (int i = 0; i < characterGenerators.size() && !randomData.found; i++) {
			characterGenerators.get(i).ConvertToRandomCharacter(randomData,
					Index);
		}
	}

	public String GeneratePassword() {

		int requiredLength = 0;

		for (int i = 0; i < characterGenerators.size(); i++) {
			IRandomCharacterGenerator randomCharacterGenerator = characterGenerators
					.get(i);
			requiredLength += randomCharacterGenerator.RequiredLength();
			if (randomCharacterGenerator.Weighting() == 0)
				return "Error, cannot set weight to 0";
		}

		if (requiredLength > length)
			return String.format("error: length %d < required %d", length,
					characterGenerators.size());

		String password;
		do {
			password = "";

			RandomData randomData = new RandomData(0);

			while (password.length() < length) {
				GenerateCharacter(randomData, password.length());
				if (!randomData.found)
					return "Invalid random character";
				else
					password = randomData.randomString;
			}
		} while (!CheckForRequiredCharacters());
		return password;
	}

	private boolean CheckForRequiredCharacters() {
		for (int i = 0; i < characterGenerators.size(); i++) {
			if (!characterGenerators.get(i).Found())
				return false;
		}
		return true;
	}
}
