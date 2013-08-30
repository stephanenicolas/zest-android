package org.zest.unittesting.easymock;

import org.zest.integrationtesting.roboguice.RoboInstrumentationTestCase;
import org.zest.unittesting.MockInjector;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import android.app.Application;

/**
 * This class will mock all dependencies of {@link RoboActivity}. <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class EasyMockRoboInstrumentationTestCase<T> extends RoboInstrumentationTestCase<T> {

	private EasyMockTestModule easyMocktestModule;
	private MockInjector mockInjector;

	public EasyMockRoboInstrumentationTestCase(Class<T> clazz) {
		super(clazz);
	}

	@Override
	protected final void beforeSetup() throws Exception {
		super.beforeSetup();
		// for android 4.3 devices / emulator only
		// https://code.google.com/p/dexmaker/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Priority%20Milestone%20Owner%20Summary&groupby=&sort=&id=2
		System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().toString());

		mockInjector = new MockInjector(this);
		easyMocktestModule = new EasyMockTestModule(mockInjector.getMapClassToMock());
		// we use both a usual module and our module
		Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
		RoboGuice.setBaseApplicationInjector(application, RoboGuice.DEFAULT_STAGE, easyMocktestModule, easyMocktestModule);
	}

	@Override
	protected void tearDown() throws Exception {
		RoboGuice.util.reset();
		mockInjector.resetAllMocks();
		super.tearDown();
	}

	// -------------------------------
	// FACADE / DELEGATION on EasyMockTestModule
	// -------------------------------
	public void resetAllMocks() {
		mockInjector.resetAllMocks();
	}

	public void replayAllMocks() {
		mockInjector.replayAllMocks();
	}

	public void verifyAllMocks() {
		mockInjector.verifyAllMocks();
	}

}