package com.example.roboguice_demo;

import org.easymock.EasyMock;

import roboguice.RoboGuice;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.google.inject.Binder;
import com.google.inject.Module;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private static final int TEST_COPUTE_RESULT = 44;
	
	public Computer mockComputer;

	public MainActivityTest() {
		super(MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dexmaker.dexcache", getActivity().getCacheDir().toString());
		mockComputer = EasyMock.createMock(Computer.class);
		RoboGuice.setBaseApplicationInjector(getActivity().getApplication(), RoboGuice.DEFAULT_STAGE, new TestModule());
	}
	
	@UiThreadTest
	public void testComputeReturns42() {
		//given
		EasyMock.expect(mockComputer.compute()).andReturn(TEST_COPUTE_RESULT);
		
		//when
		getActivity().clickOnCompute();
		
		//test
		int result = getActivity().getResult();
		assertEquals(result,42);
	}
	
	// -------------------------------
	// TEST MODULE
	//-------------------------------
	

	public class TestModule implements Module {

		@Override
		public void configure(Binder binder) {
			binder.bind(Computer.class).toInstance(mockComputer);
		}
	}
}
