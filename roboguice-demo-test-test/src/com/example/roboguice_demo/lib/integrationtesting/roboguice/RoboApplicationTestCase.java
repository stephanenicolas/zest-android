package com.example.roboguice_demo.lib.integrationtesting.roboguice;

import roboguice.activity.RoboActivity;
import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.roboguice_demo.lib.integrationtesting.MirrorInjector;

/**
 * This class will mock all dependencies of {@link RoboActivity}. <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class RoboApplicationTestCase<T extends Application> extends ApplicationTestCase<T> {

	private MirrorInjector mirrorInjector = new MirrorInjector();

	public RoboApplicationTestCase(Class<T> activityClass) {
		super(activityClass);
	}

	@Override
	public T getApplication() {
		T service = super.getApplication();
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(service, this);
		return service;
	}

}