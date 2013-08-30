package com.example.roboguice_demo.test.infra;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.zest.unittesting.easymock.EasyMockRoboInstrumentationTestCase;

import android.content.SharedPreferences;

import com.example.roboguice_demo.infra.SharedPreferencesPersister;

public class SharedPreferencesPersisterUnitTest extends EasyMockRoboInstrumentationTestCase<SharedPreferencesPersister> {

	private static final int TEST_VALUE = 46;

	public SharedPreferencesPersisterUnitTest() {
		super(SharedPreferencesPersister.class);
	}

	@Mock
	private SharedPreferences sharedPreferences;

	public void testLoad() {
		// given

		EasyMock.expect(sharedPreferences.getInt(EasyMock.anyString(), EasyMock.anyInt())).andReturn(TEST_VALUE);
		replayAllMocks();

		// when

		// test
		assertEquals(TEST_VALUE, getInstanceUnderTest().load(0));
		verifyAllMocks();
	}
}
