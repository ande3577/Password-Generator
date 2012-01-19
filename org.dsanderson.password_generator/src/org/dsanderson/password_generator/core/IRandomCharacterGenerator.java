/**
 * @author David S Anderson
 * 
 * Copyright (C) 2012
 */
package org.dsanderson.password_generator.core;

import org.dsanderson.password_generator.core.RandomData;

;

public interface IRandomCharacterGenerator {
	abstract void ConvertToRandomCharacter(RandomData randomData, int Index);

	abstract int NumberOfCharacters(int Index);

	abstract boolean Found(String password);

	abstract int Weighting();
	
	abstract int RequiredLength();

}
