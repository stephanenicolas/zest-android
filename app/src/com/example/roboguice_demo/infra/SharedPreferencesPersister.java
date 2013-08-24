package com.example.roboguice_demo.infra;

import com.google.inject.Inject;

import android.content.SharedPreferences;

public class SharedPreferencesPersister {

	private static final String SHARED_PREFS_KEY = "SHARED_PREFS_KEY";

	@Inject
	private SharedPreferences sharedPreferences;
	
	public void save(int valueToSave) {
		sharedPreferences.edit().putInt(SHARED_PREFS_KEY, valueToSave).commit();
	}

	public int load(int defaultValue) {
		return sharedPreferences.getInt(SHARED_PREFS_KEY, defaultValue);
	}
}
