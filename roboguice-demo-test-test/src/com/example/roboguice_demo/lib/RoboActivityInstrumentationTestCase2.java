package com.example.roboguice_demo.lib;

import java.lang.reflect.Field;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectFragment;
import roboguice.inject.InjectPreference;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;


/**
 * This class will mock all dependencies of {@link RoboActivity}.
 * <br>
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * @author SNI
 */
public class RoboActivityInstrumentationTestCase2<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

	public RoboActivityInstrumentationTestCase2(Class<T> activityClass) {
		super(activityClass);
	}

	@Override
	public T getActivity() {
		T activity = super.getActivity();
		for(Field field: getClass().getDeclaredFields() ) {
			Field activityField = null;
			try {
				activityField = activity.getClass().getDeclaredField( field.getName());
				if( isInjected(field) && field.getType().equals(activityField.getType())) {
					field.setAccessible(true);
					activityField.setAccessible(true);
					field.set(this, activityField.get(activity));
				}
			}
			catch (NoSuchFieldException e) {
				//nothing
			}
			catch (Exception e) {
				throw new RuntimeException( "Impossible to mirror fields in tests",e);
			}
		}
		return activity;
	}

	private boolean isInjected(Field field) {
		return (field.getAnnotation(InjectView.class) !=null ) //
				|| (field.getAnnotation(InjectResource.class) != null) //
				|| (field.getAnnotation(InjectPreference.class) != null) //
				|| (field.getAnnotation(InjectFragment.class) != null) //
				|| (field.getAnnotation(InjectExtra.class) != null);
	}
}