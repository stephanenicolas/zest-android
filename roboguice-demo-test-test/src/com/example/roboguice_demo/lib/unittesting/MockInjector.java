package com.example.roboguice_demo.lib.unittesting;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.MockType;

import android.util.Log;

/**
 * Basically : it injects the fields of stuff under test into the test as soon as they share both the same annotations, types and names.
 * This mechanism allows a test to receive all injections of class under test. They can used to write integration tests of class under test.
 * This is ideal to test a class AND the classes it depends on together, as a whole system. This is generally a faster approach than unit tests
 * as you don't have to mock complex relationships.
 * 
 * @author SNI
 */
public class MockInjector {

	private static final String MOCK_ANNOTATION_TYPE_METHOD_NAME = "type";

	private HashMap<Class<?>, Object> mapClassToMock = new HashMap<Class<?>, Object>();
	private Object test;
	private Collection<Field> fieldsAnnotatedByMock = new ArrayList<Field>();

	public MockInjector(Object test) {
		this.test = test;
		extracFieldsAnnotatedByMock();
		try {
			injectMocksIntoTest();
		}
		catch (Exception e) {
			Log.e(getClass().getSimpleName(),"This should never happen",e);
		}
	}

	public HashMap<Class<?>, Object> getMapClassToMock() {
		return mapClassToMock;
	}

	private void injectMocksIntoTest() throws IllegalArgumentException,
	IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (Field testField : fieldsAnnotatedByMock) {
			Class<?> classToMock = testField.getType();
			Object mock = createMock(classToMock, testField);
			mapClassToMock.put(classToMock, mock);
			injectMockIntoTest(test, testField, mock);
		}
	}

	private Object createMock(Class<?> classToMock, Field testField) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		MockType mockType = extractMockType(testField);
		// make more abstract to host mockito
		Object mock = createMock( classToMock, mockType);
		return mock;
	}

	//TODO support @StrictMock and @NiceMock when published
	private MockType extractMockType(Field testField) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Annotation mockAnnotation = testField.getAnnotation(Mock.class);
		MockType mockType = (MockType) mockAnnotation.annotationType().getMethod(MOCK_ANNOTATION_TYPE_METHOD_NAME).invoke(mockAnnotation);
		return mockType;
	}

	private void  extracFieldsAnnotatedByMock() {
		for (Field testField : test.getClass().getDeclaredFields()) {
			if (testField.isAnnotationPresent(Mock.class)) {
				fieldsAnnotatedByMock.add(testField);
			}
		}
	}

	private void injectMockIntoTest(Object test, Field testField, Object mock) throws IllegalAccessException {
		testField.setAccessible(true);
		testField.set(test, mock);
	}

	private <T> T createMock(Class<T> classToMock, MockType mockType) {
		switch (mockType) {
			case NICE:
				return EasyMock.createNiceMock(classToMock);

			case STRICT:
				return EasyMock.createStrictMock(classToMock);

			case DEFAULT:
			default:
				return EasyMock.createMock(classToMock);
		}
	}

	public void resetAllMocks() {
		for (Object mock : mapClassToMock.values()) {
			EasyMock.reset(mock);
		}
	}

	public void replayAllMocks() {
		for (Object mock : mapClassToMock.values()) {
			EasyMock.replay(mock);
		}
	}

	public void verifyAllMocks() {
		for (Object mock : mapClassToMock.values()) {
			EasyMock.verify(mock);
		}
	}
}