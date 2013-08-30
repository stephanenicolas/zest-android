package org.zest.integrationtesting.roboguice;

import org.zest.integrationtesting.MirrorInjector;

import roboguice.activity.RoboActivity;
import android.app.Service;
import android.test.ServiceTestCase;

/**
 * This class will mock all dependencies of {@link RoboActivity}. <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class RoboServiceTestCase<T extends Service> extends ServiceTestCase<T> {

	private MirrorInjector mirrorInjector = new MirrorInjector();

	public RoboServiceTestCase(Class<T> activityClass) {
		super(activityClass);
	}

	@Override
	public T getService() {
		T service = super.getService();
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(service, this);
		return service;
	}

}