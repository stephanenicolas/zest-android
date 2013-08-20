package com.example.roboguice_demo;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import android.os.Bundle;

import com.google.inject.Inject;

@ContentView(R.layout.activity2)
public class Activity2 extends RoboActivity {
	// -------------------------------
	// ATTRIBUTES
	//-------------------------------

	@Inject 
	private Computer computer;
	
	@Inject 
	private ComputerSingleton computerSingleton;
	
	// -------------------------------
	// LIFECYCLE
	//-------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		computerSingleton.compute();
		computer.compute();
	}
	
}
