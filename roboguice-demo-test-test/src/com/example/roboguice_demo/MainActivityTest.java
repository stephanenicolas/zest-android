package com.example.roboguice_demo;

import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public MainActivityTest() {
		super(MainActivity.class);
	}
	
	public void testComputeReturns42() {
		//given

		//when
		int result = getActivity().compute();
		
		//test
		assertEquals(result,42);
	}

}
