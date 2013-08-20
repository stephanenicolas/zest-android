package com.example.roboguice_demo;

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

	@InjectView(R.id.button_main)
	private Button buttonMain;
	
	@InjectView(R.id.button_next)
	private Button buttonNext;
	
	@InjectView(R.id.textview_main)
	private TextView textViewMain;
	
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
	protected int compute() {
		return 42;
	}
	
	// -------------------------------
	// INNER CLASS
	//-------------------------------
	
	public class OnComputeClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int result = compute();
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
