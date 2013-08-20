package com.example.roboguice_demo;

import android.test.AndroidTestCase;


public class ComputerTest extends AndroidTestCase {

	private Computer computer;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.computer = new Computer();
	}
	
	public void testComputeReturns42() {
		//given

		//when
		int result = computer.compute();
		
		//test
		assertEquals(result,42);
	}
}
