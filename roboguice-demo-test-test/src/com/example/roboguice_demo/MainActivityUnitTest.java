package com.example.roboguice_demo;

import org.easymock.EasyMock;

import roboguice.RoboGuice;
import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * This class is a unit test of MainActivity. It will mock all dependencies of {@link MainActivity}.
 * Only {@link MainActivity} will be tested, not computers.
 * <br>
 * It will use a custom RoboGuice module to bind the mocked dependencies to RoboGuice so that mocks are injected automatically inside class under test.
 * @author SNI
 */
public class MainActivityUnitTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private static final int TEST_COMPUTE_RESULT = 44;
	
	public Computer mockComputer;
	public ComputerSingleton mockComputerSingleton;

	public MainActivityUnitTest() {
		super(MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		//for android 4.3 devices / emulator only
		//https://code.google.com/p/dexmaker/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Priority%20Milestone%20Owner%20Summary&groupby=&sort=&id=2
		System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().toString());
		
		//create mocks and inject them via RoboGuice
		mockComputer = EasyMock.createMock(Computer.class);
		mockComputerSingleton = EasyMock.createMock(ComputerSingleton.class);
		
		Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
		//we use both a usual module and our module	
		RoboGuice.setBaseApplicationInjector(application, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(application), new TestModule());
	}
	
	@Override
	protected void tearDown() throws Exception {
		RoboGuice.util.reset();
		super.tearDown();
	}
	
	@UiThreadTest
	public void testComputeReturnsGoodAnswer() {
		//given
		EasyMock.expect(mockComputer.compute()).andReturn(TEST_COMPUTE_RESULT);
		EasyMock.expect(mockComputerSingleton.compute()).andReturn(TEST_COMPUTE_RESULT);

		EasyMock.replay(mockComputer);
		EasyMock.replay(mockComputerSingleton);

		//when
		getActivity().clickOnCompute();
		
		//test
		int result = getActivity().getDisplayedResult();
		assertEquals(result,TEST_COMPUTE_RESULT);
		EasyMock.verify(mockComputer);
		EasyMock.verify(mockComputerSingleton);
	}
	
	// -------------------------------
	// TEST MODULE
	//-------------------------------
	

	public class TestModule implements Module {

		@Override
		public void configure(Binder binder) {
			binder.bind(Computer.class).toInstance(mockComputer);
			binder.bind(ComputerSingleton.class).toInstance(mockComputerSingleton);
		}
	}
}
