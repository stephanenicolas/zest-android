package com.example.roboguice_demo;

import roboguice.util.Ln;

import com.google.inject.Singleton;


@Singleton
public class ComputerSingleton {
	// -------------------------------
	// CONSTANTS
	//-------------------------------

	private static final String LOG_TAG = "RoboguiceExperimentSingleton";

	//this class has no contructor

	// -------------------------------
	// PUBLCI METHODS
	//-------------------------------

	protected int compute() {
		Ln.d(LOG_TAG + this.toString() );
		return 42;
	}
}
