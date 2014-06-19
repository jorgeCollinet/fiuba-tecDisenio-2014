package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder {
	
	private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
	  
	public static final StaticLoggerBinder getSingleton() {
	    return SINGLETON;
	}
		
	private static LoggerFactory factory = new LoggerFactory();

	@Override
	public ILoggerFactory getLoggerFactory() {
		return factory;
	}

	@Override
	public String getLoggerFactoryClassStr() {
		return LoggerFactory.class.toString();
	}

}
