package com.example.roboguice_demo.test.pojo;

import android.test.AndroidTestCase;

import com.example.roboguice_demo.lib.integrationtesting.roboguice.RoboAndroidTestCase;
import com.example.roboguice_demo.pojo.Computer2;

/**
 * Tests a {@link Computer2}. As this class doesn't need any context, we can use a simple {@link AndroidTestCase}.
 * 
 * @author SNI
 */
public class Computer2IntegrationTest extends RoboAndroidTestCase<Computer2> {

	public Computer2IntegrationTest() {
		super(Computer2.class);
	}

	public void testComputeReturns42() {
		// given

		// when
		int result = getInstanceUnderTest().compute();

		// test
		assertEquals(result, 42);
	}
}
