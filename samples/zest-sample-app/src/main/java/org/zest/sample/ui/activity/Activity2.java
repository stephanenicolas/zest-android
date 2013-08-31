package org.zest.sample.ui.activity;

import org.zest.sample.infra.Computer;
import org.zest.sample.pojo.Computer2;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import android.os.Bundle;

import com.example.roboguice_demo.R;
import com.google.inject.Inject;

@ContentView(R.layout.activity2)
public class Activity2 extends RoboActivity {
	// -------------------------------
	// ATTRIBUTES
	// -------------------------------

	@Inject
	private Computer computer;

	@Inject
	private Computer2 computerSingleton;

	// -------------------------------
	// LIFECYCLE
	// -------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		computerSingleton.compute();
		computer.compute();
	}

}
