package org.dsanderson.password_generator.core;

import java.util.ArrayList;

public class PasswordGenerator {

	private Integer length;

	private ArrayList<IRandomCharacterGenerator> characterGenerators;

	public PasswordGenerator() {
		UserSettings settings = UserSettings.getInstance();
		length = settings.passwordLength;
		characterGenerators = new ArrayList<IRandomCharacterGenerator>();

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
	}

	public String GeneratePassword() {

		CompoundCharacterGenerator generator = new CompoundCharacterGenerator(
				characterGenerators);

		UserSettings settings = UserSettings.getInstance();

		if (!settings.upperCaseEnabled && !settings.lowerCaseEnabled
				&& !settings.numericEnabled && !settings.specialEnabled)
			return "Requires at least one character type";

		if (generator.RequiredLength() > length)
			return String.format("error: length %d < required %d", length,
					generator.RequiredLength());

		if (generator.Weighting() <= 0)
			return "Weight cannot be <= 0";

		if ((settings.keyword.length() != 0) && !settings.upperCaseEnabled
				&& !settings.lowerCaseEnabled)
			return "Keyword requires upper or lower case letters";

		String password;
		do {
			password = "";

			RandomData randomData = new RandomData(0);

			while (password.length() < length) {
				generator.ConvertToRandomCharacter(randomData,
						password.length());
				if (!randomData.found)
					return "Invalid random character";
				else
					password = randomData.randomString;
			}
		} while (!CheckForRequiredCharacters());
		return password;
	}

	private boolean CheckForRequiredCharacters() {
		for (int i = 0; i < characterGenerators.size(); i++) {
			if (!characterGenerators.get(i).Found())
				return false;
		}
		return true;
	}
}
