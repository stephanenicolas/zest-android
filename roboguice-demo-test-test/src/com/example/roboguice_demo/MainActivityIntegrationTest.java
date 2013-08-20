package com.example.roboguice_demo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

/**
 * This class is an integration test. It will use real computers from the {@link MainActivity}.
 * @author SNI
 */
public class MainActivityIntegrationTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private static final int TEST_COMPUTE_RESULT = 42;

	public MainActivityIntegrationTest() {
		super(MainActivity.class);
	}
	
	@UiThreadTest
	public void testComputeReturnsRightAnswer() {
		//given
		
		//when
		getActivity().clickOnCompute();
		
		//test
		int result = getActivity().getDisplayedResult();
		assertEquals(TEST_COMPUTE_RESULT,result);
	}
	
}
