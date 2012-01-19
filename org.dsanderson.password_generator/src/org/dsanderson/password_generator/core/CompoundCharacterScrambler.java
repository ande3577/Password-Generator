package org.dsanderson.password_generator.core;

import java.util.ArrayList;

public class CompoundCharacterScrambler implements IRandomCharacterGenerator {
	final ArrayList<IRandomCharacterGenerator> characterScramblers = new ArrayList<IRandomCharacterGenerator>();
	final CompoundCharacterGenerator generator;

	public CompoundCharacterScrambler(char ch) {
		UserSettings settings = UserSettings.getInstance();

		characterScramblers.clear();

		if (Character.isLetter(ch)) {
			if (settings.upperCaseEnabled)
				characterScramblers.add(new RandomCharacterGenerator(Character
						.toString(Character.toUpperCase(ch)),
						settings.upperCaseWeight, true));

			if (settings.lowerCaseEnabled)
				characterScramblers.add(new RandomCharacterGenerator(Character
						.toString(Character.toLowerCase(ch)),
						settings.lowerCaseWeight, true));
			if (settings.numericEnabled)
				characterScramblers.add(new RandomCharacterGenerator(
						getNumericString(ch), settings.numericWeight,
						!settings.upperCaseEnabled
								&& !settings.lowerCaseEnabled));
			if (settings.specialEnabled)
				characterScramblers.add(new RandomCharacterGenerator(
						getSpecialString(ch), settings.specialWeight,
						!settings.upperCaseEnabled
								&& !settings.lowerCaseEnabled));
		} else {
			characterScramblers.add(new RandomCharacterGenerator(Character
					.toString(ch), 1, true));
		}

		generator = new CompoundCharacterGenerator(characterScramblers);
	}

	@Override
	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		generator.ConvertToRandomCharacter(randomData, Index);
	}

	@Override
	public int NumberOfCharacters(int Index) {
		return generator.NumberOfCharacters(Index);
	}

	@Override
	public boolean Found() {
		return true;
	}

	@Override
	public int Weighting() {
		return generator.Weighting();
	}

	@Override
	public int RequiredLength() {
		return 1;
	}

	private String getNumericString(char ch) {
		switch ((int) Character.toLowerCase(ch)) {
		case 'i':
		case 'l':
			return "1";
		case 'o':
			return "0";
		case 'e':
			return "3";
		case 'y':
			return "4";
		case 's':
			return "5";
		case 'b':
			return "6B";
		case 'v':
			return "7";
		case 'g':
			return "9";

		default:
			return "";
		}
	}

	private String getSpecialString(char ch) {
		switch ((int) Character.toLowerCase(ch)) {
		case 'i':
			return "!|";
		case 'a':
			return "@";
		case 's':
			return "$";
		case 'o':
			return "*";
		case 'c':
			return "(";
		case 'x':
			return "+";
		default:
			return "";
		}
	}
}
