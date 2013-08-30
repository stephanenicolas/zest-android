package org.zest.integrationtesting;

import java.lang.reflect.Field;

import roboguice.inject.InjectExtra;
import roboguice.inject.InjectFragment;
import roboguice.inject.InjectPreference;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import com.google.inject.Inject;

/**
 * Basically : it injects the fields of stuff under test into the test as soon as they share both the same annotations, types and names.
 * This mechanism allows a test to receive all injections of class under test. They can used to write integration tests of class under test.
 * This is ideal to test a class AND the classes it depends on together, as a whole system. This is generally a faster approach than unit tests
 * as you don't have to mock complex relationships.
 * 
 * @author SNI
 */
public class MirrorInjector {

	public void injectSameFieldsFromStuffUnderTestIntoTest(Object instanceUnderTest, Object instanceOfTest) {
		for (Field testField : instanceOfTest.getClass().getDeclaredFields()) {
			Field underTestField = null;
			try {
				underTestField = instanceUnderTest.getClass().getDeclaredField(testField.getName());
				// TODO check they have same annotations
				if (isInjected(testField) && isInjected(testField) && haveSameType(underTestField, testField)) {
					testField.setAccessible(true);
					underTestField.setAccessible(true);
					testField.set(instanceOfTest, underTestField.get(instanceUnderTest));
				}
			}
			catch (Exception e) {
				if (isInjected(testField)) {
					throw new RuntimeException("Impossible to mirror fields in tests", e);
				}
			}
		}
	}

	private boolean isInjected(Field field) {
		return (field.getAnnotation(InjectView.class) != null) //
				|| (field.getAnnotation(InjectResource.class) != null) //
				|| (field.getAnnotation(InjectPreference.class) != null) //
				|| (field.getAnnotation(InjectFragment.class) != null) //
				|| (field.getAnnotation(InjectExtra.class) != null) //
				|| (field.getAnnotation(Inject.class) != null);
	}

	private boolean haveSameType(Field underTestField, Field testField) {
		return testField.getType().equals(underTestField.getType());
	}
}