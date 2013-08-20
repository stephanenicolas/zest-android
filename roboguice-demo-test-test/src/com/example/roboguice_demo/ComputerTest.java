package com.example.roboguice_demo;

import android.app.Application;
import android.test.InstrumentationTestCase;

/**
 * Tests a {@link ComputerSingleton}. As this class needs a context, we must use a simple {@link InstrumentationTestCase}.
 * @author SNI
 */
public class ComputerTest extends InstrumentationTestCase {

	private Computer computer;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.computer = new Computer((Application) getInstrumentation().getTargetContext().getApplicationContext());
	}
	
	public void testComputeReturns42() {
		//given

		//when
		int result = computer.compute();
		
		//test
		assertEquals(result,42);
	}
}
