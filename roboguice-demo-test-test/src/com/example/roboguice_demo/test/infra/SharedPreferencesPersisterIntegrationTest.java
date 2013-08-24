package com.example.roboguice_demo.test.infra;

import android.content.SharedPreferences;

import com.example.roboguice_demo.infra.SharedPreferencesPersister;
import com.example.roboguice_demo.lib.integrationtesting.roboguice.RoboInstrumentationTestCase;
import com.google.inject.Inject;

public class SharedPreferencesPersisterIntegrationTest extends RoboInstrumentationTestCase<SharedPreferencesPersister> {

	private static final int TEST_VALUE = 44;

	public SharedPreferencesPersisterIntegrationTest() {
		super(SharedPreferencesPersister.class);
	}

	@Inject
	private SharedPreferences sharedPreferences;

	public void testLoad() {
		// given

		// when
		sharedPreferences.edit().putInt("SHARED_PREFS_KEY", TEST_VALUE).commit();

		// test
		assertEquals(TEST_VALUE, getInstanceUnderTest().load(0));
	}
}
