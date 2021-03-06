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

package org.dsanderson.password_generator;

import org.dsanderson.password_generator.core.*;

public class ConsoleApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UserSettings settings = UserSettings.getInstance();

		for (int i = 0; i < args.length; i++) {
			String parameterName[] = args[i].split("=");

			if (parameterName[0].matches("--version")) {
				System.out.println(ProgramInfo.programName);
				System.out.println(ProgramInfo.programVersion);
				System.out.println(ProgramInfo.author);
				System.out.println(ProgramInfo.copyright);
				System.exit(0);
			}
			if (parameterName[0].matches("--length")) {
				settings.passwordLength = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--uppercase")) {
				settings.upperCaseEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--uppercaseweight")) {
				settings.upperCaseWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--lowercase")) {
				settings.lowerCaseEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--lowercaseweight")) {
				settings.lowerCaseWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--number")) {
				settings.numericEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--numberweight")) {
				settings.numericWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--special")) {
				settings.specialEnabled = !parameterName[1].matches("false");
			} else if (parameterName[0].matches("--specialweight")) {
				settings.specialWeight = Integer.parseInt(parameterName[1]);
			} else if (parameterName[0].matches("--keyword")) {
				settings.keyword = parameterName[1];
			}
		}

		PasswordGenerator generator = new PasswordGenerator();

		System.out.println(generator.GeneratePassword());
		System.exit(0);
	}
}
