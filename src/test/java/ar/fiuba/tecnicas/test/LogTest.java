package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Niveles;
import ar.fiuba.tecnicas.output.OutputConsole;

public class LogTest {

	@Test
	public void testLoadConfiguration() throws Exception{
		Log.loadConfiguration("propertiesLog.txt");
		
		//TODO: Este test esta fallando cuando se hace el container.addOutput(new OutputConsole()); dentro de OutputBuilder
		ArrayList<Logger> loggers = Log.getLoggers();
		assertEquals(1, loggers.size());
		
		/*for (Logger logger : loggers) {
			Niveles nivel = logger.getNivel();
			assertEquals(nivel,NivelInfo);
		}*/
	}

}
