/**
 * @author David S Anderson
 *
 *
 * Copyright (C) 2012 David S Anderson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.dsanderson.password_generator.core;

public class RandomCharacterGenerator implements IRandomCharacterGenerator {
	final String characters;
	final int weighting;
	final boolean canBeFirst;

	public RandomCharacterGenerator(String characters, int weighting,
			boolean canBeFirst) {
		this.characters = characters;
		this.weighting = weighting;
		this.canBeFirst = canBeFirst;
	}

	public RandomCharacterGenerator(char startingCharacter,
			char endingCharacter, int weighting, boolean canBeFirst) {
		String str = "";
		for (int index = (int) startingCharacter; index < (int) endingCharacter; index++) {
			str += (char) index;
		}
		this.characters = str;
		this.weighting = weighting;
		this.canBeFirst = canBeFirst;
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (randomData.randomNumber < NumberOfCharacters(Index)) {
			randomData.found = true;
			randomData.randomString += characters
					.charAt(randomData.randomNumber / weighting);
		} else {
			randomData.randomNumber -= NumberOfCharacters(Index);
		}
	}

	public int NumberOfCharacters(int Index) {
		if (Index == 0 && !canBeFirst)
			return 0;
		else
			return characters.length() * weighting;
	}

	public boolean Found(String password) {
		for (int index = 0; index < characters.length(); index++) {
			if (password.contains(Character.toString(characters.charAt(index))))
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
