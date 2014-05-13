package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import org.junit.Test;
import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Logger;

public class LogTest {

	@Test
	public void testLoadConfiguration() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("propertiesLog.txt")));
		Log.loadConfiguration(prop);

		// TODO: Este test esta fallando cuando se hace el
		// container.addOutput(new OutputConsole()); dentro de OutputBuilder
		ArrayList<Logger> loggers = Log.getLoggers();
		assertEquals(1, loggers.size());

		/*
		 * for (Logger logger : loggers) { Niveles nivel = logger.getNivel();
		 * assertEquals(nivel,NivelInfo); }
		 */
	}

}
