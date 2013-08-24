package com.example.roboguice_demo.lib.integrationtesting.roboguice;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import android.app.Application;
import android.test.InstrumentationTestCase;

import com.example.roboguice_demo.lib.integrationtesting.MirrorInjector;

/**
 * This class will mock all dependencies of {@link RoboActivity}. <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class RoboInstrumentationTestCase<T> extends InstrumentationTestCase {

	private Class<T> clazz;
	private T instanceUnderTest;
	private MirrorInjector testInjector = new MirrorInjector();

	public RoboInstrumentationTestCase(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		beforeSetup();
		testInjector.injectSameFieldsFromStuffUnderTestIntoTest(getInstanceUnderTest(), this);
	}

	protected void beforeSetup() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
		instanceUnderTest = null;
		super.tearDown();
	}

	public T getInstanceUnderTest() {
		if (instanceUnderTest == null) {
			Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
			instanceUnderTest = RoboGuice.getBaseApplicationInjector(application).getInstance(clazz);
		}
		return instanceUnderTest;
	}

}