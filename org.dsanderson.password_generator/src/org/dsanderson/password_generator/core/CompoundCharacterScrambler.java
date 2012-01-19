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

}
