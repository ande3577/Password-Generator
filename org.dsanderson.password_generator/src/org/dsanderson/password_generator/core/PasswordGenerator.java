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

import java.util.ArrayList;

public class PasswordGenerator {

	public PasswordGenerator() {
	}

	public String GeneratePassword() {

		UserSettings settings = UserSettings.getInstance();
		ArrayList<IRandomCharacterGenerator> characterGenerators = new ArrayList<IRandomCharacterGenerator>();

		if (settings.keyword.length() > 0)
			characterGenerators.add(new KeywordCharacterGenerator());

		if (settings.upperCaseEnabled)
			characterGenerators.add(new RandomCharacterGenerator('A', 'Z',
					settings.upperCaseWeight, true));

		if (settings.lowerCaseEnabled)
			characterGenerators.add(new RandomCharacterGenerator('a', 'z',
					settings.lowerCaseWeight, true));

		if (settings.numericEnabled)
			characterGenerators.add(new RandomCharacterGenerator('0', '9',
					settings.numericWeight,
					!(settings.upperCaseEnabled || settings.lowerCaseEnabled)));

		if (settings.specialEnabled)
			characterGenerators.add(new RandomSpecialCharacterGenerator(
					settings.specialWeight,
					!(settings.upperCaseEnabled || settings.lowerCaseEnabled)));

		CompoundCharacterGenerator generator = new CompoundCharacterGenerator(
				characterGenerators, true);

		if (!settings.upperCaseEnabled && !settings.lowerCaseEnabled
				&& !settings.numericEnabled && !settings.specialEnabled)
			return "Requires at least one character type";

		if (generator.RequiredLength() > settings.passwordLength)
			return String.format("error: length %d < required %d",
					settings.passwordLength, generator.RequiredLength());

		if (generator.Weighting() <= 0)
			return "Weight cannot be <= 0";

		if ((settings.keyword.length() != 0) && !settings.upperCaseEnabled
				&& !settings.lowerCaseEnabled)
			return "Keyword requires upper or lower case letters";

		String password;
		do {
			password = "";

			RandomData randomData = new RandomData(0);

			while (password.length() < settings.passwordLength) {
				generator.ConvertToRandomCharacter(randomData,
						password.length());
				if (!randomData.found)
					return "Invalid random character";
				else
					password = randomData.randomString;
			}
		} while (!CheckForRequiredCharacters(characterGenerators, password));
		return password;
	}

	private boolean CheckForRequiredCharacters(
			ArrayList<IRandomCharacterGenerator> characterGenerators,
			String password) {
		for (int i = 0; i < characterGenerators.size(); i++) {
			if (!characterGenerators.get(i).Found(password))
				return false;
		}
		return true;
	}
}
