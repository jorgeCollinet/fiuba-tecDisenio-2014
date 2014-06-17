package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.filter.FilterType;
import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.logging.Level;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.LoggerConfig;
import ar.fiuba.tecnicas.logging.PropertiesLoader;
import ar.fiuba.tecnicas.output.OutputType;
import ar.fiuba.tecnicas.format.Format;

public class PropertiesLoaderTest {
	protected Properties properties;
	@Before
	public void setUp() {
		properties = new Properties();
		properties.setProperty("rootLoggers",Logger.DEFAULT_NAME_LOGGER.toString()+",pepe");
		properties.setProperty("defaultFormat", Format.patronDefault);
		properties.setProperty("defaultSeparator", Format.separadorDefault);
		
		properties.setProperty("Logger." + Logger.DEFAULT_NAME_LOGGER + ".level", Level.debug.toString());
		properties.setProperty("Logger." + Logger.DEFAULT_NAME_LOGGER+".output." + OutputType.console.toString()+"0", "lalal");
		
		properties.setProperty("Logger.pepe.level", Level.fatal.toString());
		properties.setProperty("Logger.pepe.output." + OutputType.console.toString()+"0", "lilili");
		properties.setProperty("Logger.pepe.output." + OutputType.file.toString()+"0", "log1.txt");
		properties.setProperty("Logger.pepe.format." + FormatType.Format.toString()+"0", "%m");
		
	}

	@Test
	public void testLoadConfiguration() throws Exception {
		String NOMBRE_PRUEBA = "prueba1.txt";
		File file = new File(NOMBRE_PRUEBA);
		file.createNewFile();
		OutputStream out = new FileOutputStream(file);
		properties.store(out, "");
		
		out.close();
		
		ArrayList<LoggerConfig> loggersConf = PropertiesLoader.loadConfiguration(NOMBRE_PRUEBA);
		
		LoggerConfig loggerConf = loggersConf.get(0);
		assertEquals(Logger.DEFAULT_NAME_LOGGER.toString(), loggerConf.getName());
		assertEquals(Format.patronDefault, loggerConf.getDefaultFormat());
		assertEquals(Format.separadorDefault, loggerConf.getSeparator());
		assertEquals(Level.debug,loggerConf.getLevel());
		assertTrue(loggerConf.getOutputTypes().get(0) == OutputType.console);
		
		LoggerConfig loggerConf2 = loggersConf.get(1);
		assertTrue(loggerConf2.getOutputTypes().get(0) == OutputType.console);
		assertTrue(loggerConf2.getOutputTypes().get(1) == OutputType.file);
		assertEquals("pepe",loggerConf2.getName());
		assertEquals(Format.patronDefault, loggerConf2.getDefaultFormat());
		assertEquals(Format.separadorDefault, loggerConf2.getSeparator());
		assertEquals(Level.fatal, loggerConf2.getLevel());
		
		file.delete();
	}

}
