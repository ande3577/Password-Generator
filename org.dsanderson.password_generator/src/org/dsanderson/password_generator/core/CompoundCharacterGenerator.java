package org.dsanderson.password_generator.core;

import java.util.ArrayList;
import java.util.Random;

public class CompoundCharacterGenerator implements IRandomCharacterGenerator {
	private final ArrayList<IRandomCharacterGenerator> characterGenerators;

	public CompoundCharacterGenerator(
			ArrayList<IRandomCharacterGenerator> characterGenerators) {
		this.characterGenerators = characterGenerators;
	}

	@Override
	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		randomData.randomNumber = UserSettings.getInstance().random
				.nextInt(NumberOfCharacters(Index));
		randomData.found = false;

		for (int i = 0; i < characterGenerators.size() && !randomData.found; i++) {
			characterGenerators.get(i).ConvertToRandomCharacter(randomData,
					Index);
		}
	}

	@Override
	public int NumberOfCharacters(int Index) {
		int charCount = 0;
		for (int i = 0; i < characterGenerators.size(); i++) {
			charCount += characterGenerators.get(i).NumberOfCharacters(Index);
		}
		return charCount;
	}

	@Override
	public boolean Found() {
		for (int i = 0; i < characterGenerators.size(); i++) {
			if (!characterGenerators.get(i).Found())
				return false;
		}
		return true;
	}

	@Override
	public int Weighting() {
		int minWeighting = Integer.MAX_VALUE;
		for (int i = 0; i < characterGenerators.size(); i++) {
			if (characterGenerators.get(i).Weighting() < minWeighting)
				minWeighting = characterGenerators.get(i).Weighting();
		}
		return minWeighting;
	}

	@Override
	public int RequiredLength() {
		int requiredLength = 0;
		for (int i = 0; i < characterGenerators.size(); i++) {
			IRandomCharacterGenerator randomCharacterGenerator = characterGenerators
					.get(i);
			requiredLength += randomCharacterGenerator.RequiredLength();
		}
		return requiredLength;
	}

}
