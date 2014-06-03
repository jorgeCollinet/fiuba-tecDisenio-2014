package ar.fiuba.tecnicas.slf4jadapter;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import ar.fiuba.tecnicas.logging.Log;

public class LoggerFactory implements ILoggerFactory
{
	
	@Override
	/**
	 * Notas: Si un logger de ese nombre no existe devuelve null
	 * 		  No contemplamos ROOT_LOGGER_NAME porque no tenemos tal cosa
	 */
	public Logger getLogger(String arg0)
	{
		if (arg0 == null || !Log.loggerExists(arg0)) return null;
		return new LoggerAdapter(arg0);
	}
	
}
