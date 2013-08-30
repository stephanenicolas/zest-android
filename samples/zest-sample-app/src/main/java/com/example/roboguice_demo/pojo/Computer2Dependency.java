package com.example.roboguice_demo.pojo;

import com.google.inject.Singleton;

@Singleton
public class Computer2Dependency {
	public int compute() {
		return 42;
	}
}
