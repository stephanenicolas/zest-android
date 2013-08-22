package com.example.roboguice_demo.lib;

import java.lang.reflect.Field;

import org.easymock.Mock;

import roboguice.RoboGuice;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectFragment;
import roboguice.inject.InjectPreference;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;


public class EasyMockRoboActivityInstrumentationTestCase2<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

	private EasyMockTestModule easyMocktestModule;

	public EasyMockRoboActivityInstrumentationTestCase2(Class<T> activityClass) {
		super(activityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		//for android 4.3 devices / emulator only
		//https://code.google.com/p/dexmaker/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Priority%20Milestone%20Owner%20Summary&groupby=&sort=&id=2
		System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().toString());

		easyMocktestModule = new EasyMockTestModule();
		injectTestMocks();
		Application application = (Application) getInstrumentation().getTargetContext().getApplicationContext();
		//we use both a usual module and our module	
		RoboGuice.setBaseApplicationInjector(application, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(application),easyMocktestModule);
	}

	@Override
	public T getActivity() {
		T activity = super.getActivity();
		for(Field field: getClass().getDeclaredFields() ) {
			Field activityField = null;
			try {
				activityField = activity.getClass().getDeclaredField( field.getName());
			}
			catch (NoSuchFieldException e) {
				//nothing
			}
			if( activityField != null && isInjected(field) && field.getType().equals(activityField.getType())) {
				try {
					field.setAccessible(true);
					activityField.setAccessible(true);
					field.set(this, activityField.get(activity));
				}
				catch (Exception e) {
					throw new RuntimeException( "Impossible to mirror fields in tests",e);
				}
			}
		}
		return activity;
	}

	@Override
	protected void tearDown() throws Exception {
		RoboGuice.util.reset();
		easyMocktestModule.resetAllMocks();
		super.tearDown();
	}

	protected EasyMockTestModule getEasyMocktestModule() {
		return easyMocktestModule;
	}

	private void injectTestMocks() {
		for(Field field : getClass().getDeclaredFields()) {
			if( field.isAnnotationPresent(Mock.class)) {
				easyMocktestModule.addClassToMock(field.getType());
			}
		}
	}

	private boolean isInjected(Field field) {
		return (field.getAnnotation(InjectView.class) !=null ) //
				|| (field.getAnnotation(InjectResource.class) != null) //
				|| (field.getAnnotation(InjectPreference.class) != null) //
				|| (field.getAnnotation(InjectFragment.class) != null) //
				|| (field.getAnnotation(InjectExtra.class) != null);
	}

	// -------------------------------
	// FACADE / DELEGATION on EasyMockTestModule
	//-------------------------------
	public <U> U getMockOf(Class<U> clazz) {
		return easyMocktestModule.getMockOf(clazz);
	}

	public void resetAllMocks() {
		easyMocktestModule.resetAllMocks();
	}

	public void replayAllMocks() {
		easyMocktestModule.replayAllMocks();
	}

	public void verifyAllMocks() {
		easyMocktestModule.verifyAllMocks();
	}


}