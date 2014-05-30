package ar.fiuba.tenicas.slf4jadapter;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder implements LoggerFactoryBinder
{
	private static LoggerFactory factory = new LoggerFactory();
	
	@Override
	public ILoggerFactory getLoggerFactory() 
	{
		return factory;
	}

	@Override
	public String getLoggerFactoryClassStr()
	{
		return LoggerFactory.class.toString();
	}
	
}
