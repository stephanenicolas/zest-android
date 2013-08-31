package org.zest.sample.test.ui.activity;

import static org.fest.assertions.api.ANDROID.assertThat;

import org.zest.integrationtesting.roboguice.RoboActivityInstrumentationTestCase2;
import org.zest.sample.ui.activity.MainActivity;

import roboguice.inject.InjectView;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.roboguice_demo.R;

/**
 * This class is an integration test. It will use real computers from the {@link MainActivity}.
 * 
 * @author SNI
 */
public class MainActivityIntegrationTest extends RoboActivityInstrumentationTestCase2<MainActivity> {

	private static final int TEST_COMPUTE_RESULT = 42;

	@InjectView(R.id.button_main)
	private Button buttonMain;

	@InjectView(R.id.button_next)
	private Button buttonNext;

	@InjectView(R.id.textview_main)
	private TextView textViewMain;

	public MainActivityIntegrationTest() {
		super(MainActivity.class);
	}

	// adding this annotation runs the test on UI Thread and calls getActivity
	@UiThreadTest
	public void testComputeReturnsRightAnswer() {
		// given

		// when
		buttonMain.performClick();

		// test
		assertThat(textViewMain).containsText(String.valueOf(TEST_COMPUTE_RESULT));
	}

	// without the annotation, we must call getActivity() explicitly
	public void testActivityIsStarted() {
		// given

		// when

		// test
		assertThat(getActivity()).isNotFinishing();
	}

}
