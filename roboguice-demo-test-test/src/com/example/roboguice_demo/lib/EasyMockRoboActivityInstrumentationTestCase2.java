package com.example.roboguice_demo.lib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.easymock.Mock;
import org.easymock.MockType;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import android.app.Activity;
import android.app.Application;


/**
 * This class will mock all dependencies of {@link RoboActivity}.
 * <br>
 * It will use a custom RoboGuice module to bind the mocked dependencies to RoboGuice so that mocks are injected automatically inside class under test.
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * @author SNI
 */
public class EasyMockRoboActivityInstrumentationTestCase2<T extends Activity> extends RoboActivityInstrumentationTestCase2<T> {

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
	protected void tearDown() throws Exception {
		RoboGuice.util.reset();
		easyMocktestModule.resetAllMocks();
		super.tearDown();
	}

	protected EasyMockTestModule getEasyMocktestModule() {
		return easyMocktestModule;
	}

	private void injectTestMocks() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for(Field field : getClass().getDeclaredFields()) {
			if( field.isAnnotationPresent(Mock.class)) {
				Annotation mockAnnotation = field.getAnnotation(Mock.class);
				MockType mockType = (MockType) mockAnnotation.annotationType().getMethod("type").invoke(mockAnnotation);
				Class<?> classToMock = field.getType();
				switch( mockType ) {
					case NICE : 
						easyMocktestModule.addClassToNiceMock(classToMock);
						break;
						
					case STRICT : 
						easyMocktestModule.addClassToStrictMock(classToMock);
						break;
						
					case DEFAULT :
					default: 
						easyMocktestModule.addClassToMock(classToMock);
						break;

				}
			}
		}
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