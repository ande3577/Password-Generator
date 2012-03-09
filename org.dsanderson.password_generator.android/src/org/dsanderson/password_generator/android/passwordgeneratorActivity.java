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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		EditText length = (EditText) findViewById(R.id.length);

		length.setText(sharedPreferences.getString("length", "10"));

		sharedPreferences
				.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {

					@Override
					public void onSharedPreferenceChanged(
							SharedPreferences sharedPreferences, String key) {
						EditText editText = null;
						if (key == "length")
							editText = (EditText) findViewById(R.id.length);
						else if (key == "password")
							editText = (EditText) findViewById(R.id.passwordResult);
						else if (key == "keyword")
							editText = (EditText) findViewById(R.id.keyword);

						if (editText != null) {
							String oldValue = editText.getText().toString();
							String newValue = sharedPreferences.getString(key,
									oldValue);
							if (!newValue.equals(oldValue))
								editText.setText(newValue);
						}
					}
				});

		length.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				SharedPreferences sharedPreferences = PreferenceManager
						.getDefaultSharedPreferences(passwordgeneratorActivity.this);
				Editor editor = sharedPreferences.edit();
				editor.putString("length", s.toString());
				editor.apply();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.preferences:
			openPreferencesMenu();
			return true;
		case R.id.about:
			openAbout();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void openPreferencesMenu() {
		// Launch Preference activity
		Intent i = new Intent(this, PreferenceActivity.class);
		startActivity(i);
	}

	private void openAbout() {
		Intent i = new Intent(this, AboutActivity.class);
		startActivity(i);
	}

	public void onGenerateButtonClicked(View view) {
		PasswordGenerator generator = new PasswordGenerator();

		UserSettings settings = UserSettings.getInstance();

		EditText passwordResult = (EditText) findViewById(R.id.passwordResult);

		settings.passwordLength = getIntFromEditTextId(R.id.length);

		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		settings.upperCaseEnabled = sharedPreferences.getBoolean(
				"upperCaseEnabled", true);
		settings.upperCaseWeight = getInt(sharedPreferences, "upperCaseWeight",
				1);

		settings.lowerCaseEnabled = sharedPreferences.getBoolean(
				"lowerCaseEnabled", true);
		settings.lowerCaseWeight = getInt(sharedPreferences, "lowerCaseWeight",
				1);

		settings.numericEnabled = sharedPreferences.getBoolean(
				"numericEnabled", true);
		settings.numericWeight = getInt(sharedPreferences, "numericWeight", 1);

		settings.specialEnabled = sharedPreferences.getBoolean(
				"specialEnabled", false);
		settings.specialWeight = getInt(sharedPreferences, "specialWeight", 1);

		settings.keyword = getStringFromEditTextId(R.id.keyword);

		passwordResult.setText(generator.GeneratePassword());

	}

	private int getInt(SharedPreferences preference, String key, int defValue) {
		String string = preference.getString(key, Integer.toString(defValue));
		return Integer.parseInt(string);
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