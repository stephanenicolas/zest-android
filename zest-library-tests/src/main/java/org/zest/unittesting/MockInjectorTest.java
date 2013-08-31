package org.zest.unittesting;

import org.easymock.Mock;

import android.test.AndroidTestCase;


public class MockInjectorTest extends AndroidTestCase {

	private MockInjector mockInjector;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// for android 4.3 devices / emulator only
		// https://code.google.com/p/dexmaker/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Priority%20Milestone%20Owner%20Summary&groupby=&sort=&id=2
		System.setProperty("dexmaker.dexcache", getContext().getCacheDir().toString());

	}
	
	public void testGetMapClassToMock_contains_classes_to_mock_only() throws Exception {
		// GIVEN
		MockHolder mockHolder = new MockHolder();

		// WHEN
		mockInjector = new MockInjector(mockHolder);
		
		//THEN
		assertFalse( mockInjector.getMapClassToMock().isEmpty() );
		assertEquals( 1, mockInjector.getMapClassToMock().size() );
		assertFalse( mockInjector.getMapClassToMock().containsKey( String.class ) );
		assertNotNull( mockHolder.field );
		assertNull( mockHolder.field2 );
	}
	
	private static class MockHolder {
		
		@Mock
		private Object field;
		
		private Object field2;
	}
	
	
	
}
