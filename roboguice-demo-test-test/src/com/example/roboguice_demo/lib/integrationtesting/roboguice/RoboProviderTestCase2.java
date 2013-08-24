package com.example.roboguice_demo.lib.integrationtesting.roboguice;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import android.app.Application;
import android.content.ContentProvider;
import android.test.ProviderTestCase2;

import com.example.roboguice_demo.lib.integrationtesting.MirrorInjector;

/**
 * This class will mock all dependencies of {@link RoboActivity}. <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class RoboProviderTestCase2<T extends ContentProvider> extends ProviderTestCase2<T> {

	private MirrorInjector mirrorInjector = new MirrorInjector();
	private Class<T> clazz;

	private T instanceUnderTest;

	public RoboProviderTestCase2(Class<T> clazz, String authority) {
		super(clazz, authority);
		this.clazz = clazz;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		beforeSetup();
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(getInstanceUnderTest(), this);
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
			Application application = (Application) getContext().getApplicationContext();
			instanceUnderTest = RoboGuice.getBaseApplicationInjector(application).getInstance(this.clazz);
		}
		return instanceUnderTest;
	}

}