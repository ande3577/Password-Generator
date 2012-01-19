/**
 * @author David S Anderson
 * 
 * Copyright (C) 2012
 */

package org.dsanderson.password_generator.core;

import java.util.ArrayList;

public class RandomSpecialCharacterGenerator implements
		IRandomCharacterGenerator {
	final int weighting;
	final CompoundCharacterGenerator generator;

	private char STARTING_CHARACTERS[] = { '!', ':', '[', '{' };
	private char ENDING_CHARACTERS[] = { '/', '@', '`', '~' };

	private ArrayList<IRandomCharacterGenerator> specialCharacterGenerators;

	public RandomSpecialCharacterGenerator(int Weighting, boolean canBeFirst) {
		weighting = Weighting;

		specialCharacterGenerators = new ArrayList<IRandomCharacterGenerator>();

		for (int i = 0; i < STARTING_CHARACTERS.length; i++) {
			specialCharacterGenerators.add(new RandomCharacterGenerator(
					STARTING_CHARACTERS[i], ENDING_CHARACTERS[i], Weighting,
					canBeFirst));
		}
		generator = new CompoundCharacterGenerator(specialCharacterGenerators,
				false);
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		generator.ConvertToRandomCharacter(randomData, Index);
	}

	public int NumberOfCharacters(int Index) {
		return generator.NumberOfCharacters(Index);
	}

	public boolean Found(String password) {
		for (int i = 0; i < specialCharacterGenerators.size(); i++) {
			if (specialCharacterGenerators.get(i).Found(password))
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
