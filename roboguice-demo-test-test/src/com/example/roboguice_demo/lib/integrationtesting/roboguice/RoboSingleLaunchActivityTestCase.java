package com.example.roboguice_demo.lib.integrationtesting.roboguice;

import roboguice.activity.RoboActivity;
import android.app.Activity;
import android.test.SingleLaunchActivityTestCase;

import com.example.roboguice_demo.lib.integrationtesting.MirrorInjector;

/**
 * This class will mock all dependencies of {@link RoboActivity}. <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class RoboSingleLaunchActivityTestCase<T extends Activity> extends SingleLaunchActivityTestCase<T> {

	private MirrorInjector mirrorInjector = new MirrorInjector();

	public RoboSingleLaunchActivityTestCase(String packageName, Class<T> activityClass) {
		super(packageName,activityClass);
	}

	@Override
	public T getActivity() {
		T activity = super.getActivity();
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(activity, this);
		return activity;
	}

}