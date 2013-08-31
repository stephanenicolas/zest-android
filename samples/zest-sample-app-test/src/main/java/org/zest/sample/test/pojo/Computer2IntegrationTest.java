package org.zest.sample.test.pojo;

import org.zest.integrationtesting.roboguice.RoboAndroidTestCase;
import org.zest.sample.pojo.Computer2;

import android.test.AndroidTestCase;

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
