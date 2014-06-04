package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Properties;
import ar.fiuba.tecnicas.logging.Niveles;
import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.slf4jadapter.StaticLoggerBinder;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class slf4jTest 
{
	private String nombreLogger = "";
	private ILoggerFactory factory;
	private ByteArrayOutputStream outputConsola;
	private PrintStream viejaConsola = System.out;
	
	@Before
    public void setUp() throws Exception
    {
		Properties properties = new Properties();
		properties.setProperty("Separador", "-");
		properties.setProperty("FormatoDefault", "%m");
		properties.setProperty(Niveles.debug.toString(),nombreLogger+",Output>console");
		Log.loadConfiguration(properties);
		factory = new StaticLoggerBinder().getLoggerFactory();
		
		outputConsola = new ByteArrayOutputStream();
	    PrintStream printStream = new PrintStream(outputConsola, true);
	    System.setOut(printStream);
	}
	
	@After
	public void tearDown()
	{
		 System.setOut(viejaConsola);
	}
	
	
	@Test
	public void testLog() 
	{
		Logger logger = factory.getLogger(nombreLogger);
		logger.error("Test");
		assertEquals("Test", outputConsola.toString().trim());	
	}
	
	@Test
	public void testNoLog() 
	{
		Logger logger = factory.getLogger(nombreLogger);
		logger.trace("Test");
		assertEquals("", outputConsola.toString().trim());	
	}
	
	@Test
	public void testCheckNivel()
	{
		Logger logger = factory.getLogger(nombreLogger);
		assertTrue(logger.isDebugEnabled());
		assertFalse(logger.isTraceEnabled());
	}

}