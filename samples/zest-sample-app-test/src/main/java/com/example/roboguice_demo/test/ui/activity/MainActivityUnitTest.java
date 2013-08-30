package com.example.roboguice_demo.test.ui.activity;

import static org.fest.assertions.api.ANDROID.assertThat;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.zest.unittesting.easymock.EasyMockRoboActivityInstrumentationTestCase2;

import roboguice.inject.InjectView;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.roboguice_demo.R;
import com.example.roboguice_demo.infra.Computer;
import com.example.roboguice_demo.pojo.Computer2;
import com.example.roboguice_demo.ui.activity.MainActivity;

/**
 * This class is a unit test of MainActivity. It will mock all dependencies of {@link MainActivity}.
 * Only {@link MainActivity} will be tested, not computers. <br>
 * It will use a custom RoboGuice module to bind the mocked dependencies to RoboGuice so that mocks are injected automatically inside class under test.
 * It will receive the same injections as Activity under test if fields are declared in the same way as in this activity (same name, type and annotation).
 * 
 * @author SNI
 */
public class MainActivityUnitTest extends EasyMockRoboActivityInstrumentationTestCase2<MainActivity> {

	private static final int TEST_COMPUTE_RESULT = 44;

	@InjectView(R.id.button_main)
	private Button buttonMain;

	@InjectView(R.id.button_next)
	private Button buttonNext;

	@InjectView(R.id.textview_main)
	private TextView textViewMain;

	@Mock
	public Computer mockComputer;
	@Mock
	public Computer2 mockComputerSingleton;

	public MainActivityUnitTest() {
		super(MainActivity.class);
	}

	@UiThreadTest
	public void testComputeReturnsGoodAnswer() {
		// given
		EasyMock.expect(mockComputer.compute()).andReturn(TEST_COMPUTE_RESULT);
		EasyMock.expect(mockComputerSingleton.compute()).andReturn(TEST_COMPUTE_RESULT);

		replayAllMocks();

		// when
		buttonMain.performClick();

		// test
		assertThat(textViewMain).containsText(String.valueOf(TEST_COMPUTE_RESULT));
		verifyAllMocks();
	}
}
