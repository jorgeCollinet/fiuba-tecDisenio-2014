package ar.fiuba.tecnicas.slf4jadapter;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class LoggerFactory implements ILoggerFactory
{
	
	@Override
	public Logger getLogger(String arg0)
	{
		// TODO verificar si esta forma logra funcionar al 100%
		LoggerAdapter adapter = new LoggerAdapter(arg0);
		return adapter;
	}
	
}
