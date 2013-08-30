package com.example.roboguice_demo.infra;

import roboguice.util.Ln;
import android.app.Application;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Computer {

	// -------------------------------
	// CONSTANTS
	// -------------------------------

	private static final String LOG_TAG = "RoboguiceExperiment";

	// -------------------------------
	// ATTRIBUTES
	// -------------------------------

	private Application context;

	// -------------------------------
	// CONSTRUCTOR
	// -------------------------------

	@Inject
	public Computer(Application context) {
		this.context = context;
	}

	// -------------------------------
	// PUBLIC METHODS
	// -------------------------------

	public int compute() {
		Ln.d(LOG_TAG + context.getClass().getSimpleName());
		Ln.d(LOG_TAG + this.toString());
		return 42;
	}
}
