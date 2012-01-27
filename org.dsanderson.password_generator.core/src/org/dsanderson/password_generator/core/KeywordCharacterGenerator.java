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

public class KeywordCharacterGenerator implements IRandomCharacterGenerator {
	private final int startingIndex;
	private boolean found;;

	public KeywordCharacterGenerator() {
		UserSettings settings = UserSettings.getInstance();
		startingIndex = settings.random
				.nextInt(UserSettings.getInstance().passwordLength
						- settings.keyword.length() + 1);
		found = false;
	}

	public void ConvertToRandomCharacter(RandomData randomData, int Index) {
		if (Index == startingIndex) {
			String keyword = UserSettings.getInstance().keyword;

			for (int i = 0; i < keyword.length(); i++) {

				CompoundCharacterScrambler scrambler = new CompoundCharacterScrambler(
						keyword.charAt(i));
				randomData.randomNumber = UserSettings.getInstance().random
						.nextInt(scrambler.NumberOfCharacters(Index));
				scrambler.ConvertToRandomCharacter(randomData, Index);
			}
			randomData.found = true;
			found = true;
		}
	}

	public int NumberOfCharacters(int Index) {
		return 0;
	}

	public boolean Found(String password) {
		return found;
	}

	public int Weighting() {
		return 1;
	}

	public int RequiredLength() {
		UserSettings settings = UserSettings.getInstance();
		int requiredLength = settings.keyword.length();
		if (settings.upperCaseEnabled && requiredLength > 0)
			requiredLength--;
		if (settings.lowerCaseEnabled && requiredLength > 0)
			requiredLength--;
		return requiredLength;
	}

}
