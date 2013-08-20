package com.example.roboguice_demo;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

	// -------------------------------
	// ATTRIBUTES
	//-------------------------------

	@InjectView(R.id.button_main)
	private Button buttonMain;
	
	@InjectView(R.id.button_next)
	private Button buttonNext;
	
	@InjectView(R.id.textview_main)
	private TextView textViewMain;
	
	@Inject 
	private Computer computer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initializeViews();
	}
	
	// -------------------------------
	// PRIVATE METHODS
	//-------------------------------


	private void initializeViews() {
		buttonMain.setOnClickListener( new OnComputeClickListener());
		buttonNext.setOnClickListener( new OnGoToNextActivityClickListener());
	}
	
	//protected for testing
	protected void clickOnCompute() {
		buttonMain.callOnClick();
	}
	
	//protected for testing
	protected int getDisplayedResult() {
		return Integer.parseInt((String) textViewMain.getText());
	}
	
	// -------------------------------
	// INNER CLASS
	//-------------------------------
	
	public class OnComputeClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int result = computer.compute();
			textViewMain.setText( String.valueOf(result));
		}
	}

	public class OnGoToNextActivityClickListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			startActivity(new Intent(MainActivity.this, Activity2.class));
		}
	}

}
