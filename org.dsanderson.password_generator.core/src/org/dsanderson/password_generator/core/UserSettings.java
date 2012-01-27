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

import java.util.Random;

public class UserSettings {
	static UserSettings instancePtr = null;

	public boolean upperCaseEnabled = true, lowerCaseEnabled = true,
			numericEnabled = true, specialEnabled = false;
	public int upperCaseWeight = 1, lowerCaseWeight = 1, numericWeight = 1,
			specialWeight = 1;
	public int passwordLength = 10;
	public Random random = new Random();
	public String keyword = "";

	private UserSettings() {

	}

	static public UserSettings getInstance() {
		if (instancePtr == null)
			instancePtr = new UserSettings();

		return instancePtr;
	}

}
