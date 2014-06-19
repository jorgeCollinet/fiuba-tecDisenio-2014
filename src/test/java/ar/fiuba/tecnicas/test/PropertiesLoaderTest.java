package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.logging.Level;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.LoggerConfig;
import ar.fiuba.tecnicas.logging.PropertiesLoader;
import ar.fiuba.tecnicas.output.OutputType;
import ar.fiuba.tecnicas.format.Format;

public class PropertiesLoaderTest {
	protected Properties properties;
	
	public static Properties generateTestProperties() {
		Properties properties = new Properties();
		properties.setProperty("rootLoggers",Logger.DEFAULT_NAME_LOGGER.toString()+",pepe");
		properties.setProperty("defaultFormat", Format.defaultPattern);
		properties.setProperty("defaultSeparator", Format.defaultSeparator);
		
		properties.setProperty("Logger." + Logger.DEFAULT_NAME_LOGGER + ".level", Level.debug.toString());
		properties.setProperty("Logger." + Logger.DEFAULT_NAME_LOGGER+".output." + OutputType.console.toString()+"0", "lalal");
		
		properties.setProperty("Logger.pepe.level", Level.fatal.toString());
		properties.setProperty("Logger.pepe.output." + OutputType.console.toString()+"0", "lilili");
		properties.setProperty("Logger.pepe.output." + OutputType.file.toString()+"0", "log1.txt");
		properties.setProperty("Logger.pepe.format." + FormatType.Format.toString()+"0", "%m");
		
		return properties;
	}
	
	@Before
	public void setUp()	{
		this.properties = generateTestProperties();
	}

	@Test
	public void testLoadConfiguration() throws Exception {
		ByteArrayOutputStream propMockFile = new ByteArrayOutputStream();
		properties.store(propMockFile, "");
		InputStream inputProperties = new StringBufferInputStream(propMockFile.toString());
		propMockFile.close();
		
		List<LoggerConfig> loggersConf = PropertiesLoader.loadConfiguration(inputProperties);
		
		LoggerConfig loggerConf = loggersConf.get(0);
		assertEquals(Logger.DEFAULT_NAME_LOGGER.toString(), loggerConf.getName());
		assertEquals(Format.defaultPattern, loggerConf.getDefaultFormat());
		assertEquals(Format.defaultSeparator, loggerConf.getSeparator());
		assertEquals(Level.debug,loggerConf.getLevel());
		assertTrue(loggerConf.getOutputTypes().get(0) == OutputType.console);
		
		LoggerConfig loggerConf2 = loggersConf.get(1);
		assertTrue(loggerConf2.getOutputTypes().get(0) == OutputType.console);
		assertTrue(loggerConf2.getOutputTypes().get(1) == OutputType.file);
		assertEquals("pepe",loggerConf2.getName());
		assertEquals(Format.defaultPattern, loggerConf2.getDefaultFormat());
		assertEquals(Format.defaultSeparator, loggerConf2.getSeparator());
		assertEquals(Level.fatal, loggerConf2.getLevel());
	}

}
