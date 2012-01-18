/**
 * 
 */
package org.dsanderson.password_generator.core;

import org.dsanderson.password_generator.core.RandomData;;

/**
 * @author dsanderson
 *
 */
public interface IRandomCharacterGenerator {
	abstract void ConvertToRandomCharacter(RandomData randomData, int Index);

	abstract int NumberOfCharacters(int Index);
	
	abstract boolean Found();
	
	abstract int Weighting();
	
}
