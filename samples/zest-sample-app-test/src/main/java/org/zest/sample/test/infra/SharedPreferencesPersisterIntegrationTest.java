package org.zest.sample.test.infra;

import org.zest.integrationtesting.roboguice.RoboInstrumentationTestCase;
import org.zest.sample.infra.SharedPreferencesPersister;

import android.content.SharedPreferences;

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
