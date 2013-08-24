package com.example.roboguice_demo.lib.unittesting.easymock;

import java.util.HashMap;

import com.google.inject.Binder;
import com.google.inject.Module;

public class EasyMockTestModule implements Module {

	private HashMap<Class<?>, Object> mapClassToMock = new HashMap<Class<?>, Object>();

	public EasyMockTestModule(HashMap<Class<?>, Object> mapClassToMock) {
		this.mapClassToMock = mapClassToMock;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void configure(Binder binder) {
		for (Class classToMock : mapClassToMock.keySet()) {
			Object mock = mapClassToMock.get(classToMock);
			binder.bind(classToMock).toInstance(mock);
		}
	}
	
	public HashMap<Class<?>, Object> getMapClassToMock() {
		return mapClassToMock;
	}
}
