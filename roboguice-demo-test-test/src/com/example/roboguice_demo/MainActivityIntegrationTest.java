package com.example.roboguice_demo;

import static org.fest.assertions.api.ANDROID.assertThat;
import roboguice.inject.InjectView;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.roboguice_demo.lib.RoboActivityInstrumentationTestCase2;

/**
 * This class is an integration test. It will use real computers from the {@link MainActivity}.
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
	
	@UiThreadTest
	public void testComputeReturnsRightAnswer() {
		//given
		
		//when
		buttonMain.performClick();
		
		//test
		assertThat(textViewMain).containsText(String.valueOf(TEST_COMPUTE_RESULT));
	}
	
}
