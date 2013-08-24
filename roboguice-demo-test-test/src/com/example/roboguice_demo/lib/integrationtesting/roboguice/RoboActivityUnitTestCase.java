package com.example.roboguice_demo.lib.integrationtesting.roboguice;

import roboguice.activity.RoboActivity;
import android.app.Activity;
import android.test.ActivityUnitTestCase;

import com.example.roboguice_demo.lib.integrationtesting.MirrorInjector;

/**
 * This class will mock all dependencies of {@link RoboActivity}. <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class RoboActivityUnitTestCase<T extends Activity> extends ActivityUnitTestCase<T> {

	private MirrorInjector mirrorInjector = new MirrorInjector();

	public RoboActivityUnitTestCase(Class<T> activityClass) {
		super(activityClass);
	}

	@Override
	public T getActivity() {
		T activity = super.getActivity();
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(activity, this);
		return activity;
	}

}