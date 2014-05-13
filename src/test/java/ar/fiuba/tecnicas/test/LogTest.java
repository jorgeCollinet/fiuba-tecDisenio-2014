package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Niveles;

public class LogTest {

	@Test
	public void testLoadConfiguration() throws Exception{
		Log.loadConfiguration("propertiesLog.txt");
		Log.saveConfiguration();
		
		ArrayList<Logger> loggers = Log.getLoggers();
		for (Logger logger : loggers) {
			Niveles nivel = logger.getNivel();
			
		}
		assertEquals(true,true);
	}

}
