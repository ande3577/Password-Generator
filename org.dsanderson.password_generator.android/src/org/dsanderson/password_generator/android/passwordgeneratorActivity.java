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

package org.dsanderson.password_generator.android;

import org.dsanderson.passwordgenerator.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import org.dsanderson.password_generator.core.*;

public class passwordgeneratorActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onGenerateButtonClicked(View view) {
		PasswordGenerator generator = new PasswordGenerator();

		UserSettings settings = UserSettings.getInstance();

		EditText passwordResult = (EditText) findViewById(R.id.passwordResult);

		settings.passwordLength = getIntFromEditTextId(R.id.length);

		settings.upperCaseEnabled = getCheckedFromButtonId(R.id.upperCaseEnabled);
		settings.upperCaseWeight = getIntFromEditTextId(R.id.upperCaseWeight);

		settings.lowerCaseEnabled = getCheckedFromButtonId(R.id.lowerCaseEnabled);
		settings.lowerCaseWeight = getIntFromEditTextId(R.id.lowerCaseWeight);

		settings.numericEnabled = getCheckedFromButtonId(R.id.numericEnabled);
		settings.numericWeight = getIntFromEditTextId(R.id.numericWeight);

		settings.specialEnabled = getCheckedFromButtonId(R.id.specialEnabled);
		settings.specialWeight = getIntFromEditTextId(R.id.specialWeight);
		
		settings.keyword = getStringFromEditTextId(R.id.keyword);

		passwordResult.setText(generator.GeneratePassword());

	}

	private int getIntFromEditTextId(int id) {
		return Integer.parseInt(((EditText) findViewById(id)).getText()
				.toString());
	}

	private boolean getCheckedFromButtonId(int id) {
		return ((CheckBox) findViewById(id)).isChecked();
	}

	private String getStringFromEditTextId(int id) {
		return ((EditText) findViewById(id)).getText().toString().trim();
	}
}