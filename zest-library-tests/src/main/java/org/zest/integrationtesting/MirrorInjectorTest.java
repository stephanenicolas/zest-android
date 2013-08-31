package org.zest.integrationtesting;

import roboguice.inject.InjectView;
import android.test.AndroidTestCase;

public class MirrorInjectorTest extends AndroidTestCase {

	private MirrorInjector mirrorInjector;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mirrorInjector = new MirrorInjector();
	}

	public void testInjectSameFieldsFromStuffUnderTestIntoTest_copies_injectedField() {
		// GIVEN
		SourceA sourceA = new SourceA();
		sourceA.field = "blah";
		DestinationA destinationA = new DestinationA();

		// WHEN
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(sourceA, destinationA);

		// THEN
		assertEquals("blah", destinationA.field);
	}

	public void testInjectSameFieldsFromStuffUnderTestIntoTest_doesnt_copy_fields_with_no_annotations() {
		// GIVEN
		SourceA sourceA = new SourceA();
		sourceA.field2 = "blah";
		DestinationA destinationA = new DestinationA();

		// WHEN
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(sourceA, destinationA);

		// THEN
		assertNotSame("blah", destinationA.field2);
	}
	
	public void testInjectSameFieldsFromStuffUnderTestIntoTest_doesnt_copy_fields_with_no_destination_annotations() {
		// GIVEN
		SourceA sourceA = new SourceA();
		sourceA.field3 = "blah";
		DestinationA destinationA = new DestinationA();

		// WHEN
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(sourceA, destinationA);

		// THEN
		assertNotSame("blah", destinationA.field3);
	}

	public void testInjectSameFieldsFromStuffUnderTestIntoTest_doesnt_copy_fields_with_no_source_annotations() {
		// GIVEN
		SourceA sourceA = new SourceA();
		sourceA.field4 = "blah";
		DestinationA destinationA = new DestinationA();

		// WHEN
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(sourceA, destinationA);

		// THEN
		assertNotSame("blah", destinationA.field4);
	}

	public void testInjectSameFieldsFromStuffUnderTestIntoTest_doesnt_copy_fields_with_no_common_types() {
		// GIVEN
		SourceA sourceA = new SourceA();
		sourceA.field5 = 2;
		DestinationA destinationA = new DestinationA();

		// WHEN
		mirrorInjector.injectSameFieldsFromStuffUnderTestIntoTest(sourceA, destinationA);

		// THEN
		assertNotSame(2, destinationA.field5);
	}

	@SuppressWarnings("unused")
	private static class SourceA {

		@InjectView
		private String field;

		private String field2;

		@InjectView
		private String field3;

		private String field4;
		
		@InjectView
		private Integer field5;
	}

	private static class DestinationA {

		@InjectView
		private String field;

		private String field2;

		private String field3;
		
		@InjectView
		private String field4;

		@InjectView
		private Double field5;

	}

}
