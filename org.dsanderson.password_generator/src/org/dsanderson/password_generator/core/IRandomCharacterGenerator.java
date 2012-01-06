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
	abstract RandomData ConvertToRandomCharacter(int RandomNumber);

	abstract int NumberOfCharacters();
}
