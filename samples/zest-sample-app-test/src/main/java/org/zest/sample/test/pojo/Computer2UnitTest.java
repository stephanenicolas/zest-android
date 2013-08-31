package org.zest.sample.test.pojo;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.zest.sample.pojo.Computer2;
import org.zest.sample.pojo.Computer2Dependency;
import org.zest.unittesting.easymock.EasyMockRoboAndroidTestCase;

import android.test.AndroidTestCase;

/**
 * Tests a {@link Computer2}. As this class doesn't need any context, we can use a simple {@link AndroidTestCase}.
 * 
 * @author SNI
 */
public class Computer2UnitTest extends EasyMockRoboAndroidTestCase<Computer2> {

	private static final int TEST_VALUE = 48;

	@Mock
	private Computer2Dependency computer2Dependency;

	public Computer2UnitTest() {
		super(Computer2.class);
	}

	public void testComputeReturns42() {
		// given
		EasyMock.expect(computer2Dependency.compute()).andReturn(TEST_VALUE);
		replayAllMocks();

		// when
		int result = getInstanceUnderTest().compute();

		// test
		assertEquals(result, TEST_VALUE);
		verifyAllMocks();
	}
}
