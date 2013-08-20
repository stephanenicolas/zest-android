package com.example.roboguice_demo;

import android.test.AndroidTestCase;

/**
 * Tests a {@link ComputerSingleton}. As this class doesn't need any context, we can use a simple {@link AndroidTestCase}.
 * @author SNI
 */
public class SingletonComputerTest extends AndroidTestCase {

	private ComputerSingleton computer;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.computer = new ComputerSingleton();
	}
	
	public void testComputeReturns42() {
		//given

		//when
		int result = computer.compute();
		
		//test
		assertEquals(result,42);
	}
}
