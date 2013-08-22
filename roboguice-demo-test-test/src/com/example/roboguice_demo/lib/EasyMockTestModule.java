package com.example.roboguice_demo.lib;

import java.util.HashMap;

import org.easymock.EasyMock;

import com.google.inject.Binder;
import com.google.inject.Module;


public class EasyMockTestModule  implements Module {

	private HashMap<Class<?>, Object> mapClassToMock = new HashMap<Class<?>, Object>();

	public EasyMockTestModule() {
	}
	
	public EasyMockTestModule(Class<?>... classesToMock) {
		for( Class<?> classToMock : classesToMock ) {
			addClassToMock(classToMock);
		}
	}

	public void addClassToMock(Class<?> classToMock) {
		mapClassToMock .put( classToMock, EasyMock.createMock(classToMock));
	}
	
	public void addClassToNiceMock(Class<?> classToMock) {
		mapClassToMock .put( classToMock, EasyMock.createNiceMock(classToMock));
	}

	public void addClassToStrictMock(Class<?> classToMock) {
		mapClassToMock .put( classToMock, EasyMock.createStrictMock(classToMock));
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void configure(Binder binder) {
		System.out.println("Configure called");
		for( Class classToMock : mapClassToMock.keySet() ) {
			Object mock = mapClassToMock.get(classToMock);
			binder.bind(classToMock).toInstance(mock);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getMockOf( Class<T> clazz ) {
		return (T) mapClassToMock.get(clazz);
	}

	public void resetAllMocks() {
		for( Object mock : mapClassToMock.values()) {
			EasyMock.reset(mock);
		}
	}

	public void replayAllMocks() {
		for( Object mock : mapClassToMock.values()) {
			EasyMock.replay(mock);
		}
	}

	public void verifyAllMocks() {
		for( Object mock : mapClassToMock.values()) {
			EasyMock.verify(mock);
		}
	}

}
