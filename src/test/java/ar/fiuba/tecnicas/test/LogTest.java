package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Level;
import ar.fiuba.tecnicas.output.OutputType;

public class LogTest 
{
	private static ByteArrayOutputStream outputConsola;
	private static PrintStream viejaConsola;
	private static InputStream inputProperties;
	private static InputStream inputXML;

	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUp() throws Exception {
		// Genera los archivos properties y xml en ram al iniciar los tests
		// Como son inputs de solo lectura esto se hace una sola vez
		Properties properties = PropertiesLoaderTest.generateTestProperties();
		ByteArrayOutputStream propMockFile = new ByteArrayOutputStream();
		properties.store(propMockFile, "");
		inputProperties = new StringBufferInputStream(propMockFile.toString());
		inputXML = new StringBufferInputStream(XMLLoaderTest.generateTestXML());
		viejaConsola = System.out;
		outputConsola = new ByteArrayOutputStream();
	    PrintStream printStream = new PrintStream(outputConsola, true);
	    System.setOut(printStream);
	}
	
	@Before
	public void reset() throws IOException 
	{
		outputConsola.reset();
		inputProperties.reset();
		inputXML.reset();
	}

	@AfterClass
	public static void tearDown() throws IOException
	{
		inputProperties.close();
		inputXML.close();
		System.setOut(viejaConsola);
	}
	
	@Before
	@After
	public void deleteFiles()
	{
		File file = new File("log1.txt");
		if (file.exists()) file.delete();
	}
	
	/*@Test
	public void loadConfiguration() throws Exception {
		Properties properties = generateDefaultTestPropertie();

		Log.loadConfiguration(properties);

		ArrayList<Logger> loggers = Log.getLoggers();
		assertEquals(2, loggers.size());

		Logger loggerFatal = loggers.get(1);
		assertEquals(loggerFatal.getNivel(), Level.fatal);
		OutputContainer outputContainerFatal = (OutputContainer) loggerFatal.getOutput();
		ArrayList<IOutput> outputsDebbug = outputContainerFatal.getOutputs();
		assertEquals(2, outputsDebbug.size());
		IOutput outputConsoleWarning = outputsDebbug.get(0);
		assertTrue(outputConsoleWarning instanceof OutputConsole);
		IOutput outputFileWarning = outputsDebbug.get(1);
		assertTrue(outputFileWarning instanceof OutputFile);
		
		Logger loggerDebug = loggers.get(0);
		assertEquals(loggerDebug.getNivel(), Level.debug);
		OutputContainer outputContainerDebug = (OutputContainer) loggerDebug.getOutput();
		ArrayList<IOutput> outputsInfo = outputContainerDebug.getOutputs();
		assertEquals(1, outputsInfo.size());
		IOutput outputConsoleInfo = outputsInfo.get(0);
		assertTrue(outputConsoleInfo instanceof OutputConsole);
	}*/
	
	@Test
	public void logearConfInXml() throws Exception {
		Log.loadConfigurationFromXML(inputXML);
		Log.log(Level.trace, "Test", "logger1");
		assertEquals("", outputConsola.toString().trim());	
		Log.log(Level.debug, "Test", "logger1");
		assertEquals("{'thread': 'main'}", outputConsola.toString().trim());	
		outputConsola.reset();
		Log.log(Level.fatal, "Test", "logger2");
		assertEquals("Test", outputConsola.toString().trim());
		outputConsola.reset();
		Log.log(Level.warning, "Test", "logger3");
		assertEquals("Test", outputConsola.toString().trim());	
	}
	
	@Test
	public void logearConfDefault() throws Exception {
		Log.loadConfigurationDefault();
		Log.log(Level.trace, "Test", "defaultLogger");
		assertEquals("", outputConsola.toString().trim());	
		Log.log(Level.info, "Test", "defaultLogger");
		assertEquals("Test", outputConsola.toString().trim());	
	}

	@Test
	public void logearConfInProperties() throws Exception {
		Log.loadConfigurationFromProperties(inputProperties);
		auxLogear("logearConfInProperties(): mensaje", Level.fatal, "pepe", true);
	}
	
	public void auxLogear(String mensaje, Level nivel, String nombreLogger, boolean assertConsole) 
			throws Exception {
		Log.log(nivel, mensaje, nombreLogger);

		File file = new File("log1.txt");
		assertTrue(file.exists());
		assertEquals(mensaje, FileHelper.getLastMessageLogged("log1.txt"));

		if (assertConsole) {
			assertEquals(mensaje, outputConsola.toString().trim());
		}
	}

	@Test
	public void noLogearConfFromXmlConDistintoNivelMismoNombre() throws Exception {
		Log.loadConfigurationFromXML(inputXML);
		noLogearArchivoAux(Level.debug,"pepe");
	}

	@Test
	public void noLogearConfFromPropertiesConDistintoNivelMismoNombre() throws Exception {
		Log.loadConfigurationFromProperties(inputProperties);
		noLogearArchivoAux(Level.debug,"pepe");
	}
	@Test
	public void noLogearConfFromPropertiesConMismoNivelDistintoNombre() throws Exception {
		Log.loadConfigurationFromProperties(inputProperties);
		noLogearArchivoAux(Level.fatal,"juan el pastor");
	}
	
	public void noLogearArchivoAux(Level nivel, String nombreLogger) throws Exception {
		Log.log(nivel, "public void noLogearArchivoAux: mensaje "+nombreLogger+"|"+nivel.toString(), nombreLogger);
		
		File file = new File("log1.txt");
		assertTrue(!file.exists());
		assertTrue(file.length() == 0);
	}
}
