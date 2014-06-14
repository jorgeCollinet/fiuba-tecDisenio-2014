package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Level;
import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputConsole;
import ar.fiuba.tecnicas.output.OutputContainer;
import ar.fiuba.tecnicas.output.OutputFile;
import ar.fiuba.tecnicas.output.OutputType;

public class LogTest {
	static String NOMBRE_ARCHIVO1_PRUEBA = "propertiesLog.txt";
	protected ByteArrayOutputStream outputConsola;
	protected PrintStream viejaConsola;
	

	protected Properties generateDefaultTestPropertie() {
		Properties properties = new Properties();
		properties.setProperty("rootLoggers",Logger.DEFAULT_NAME_LOGGER.toString()+",pepe");
		properties.setProperty("defaultFormat", Format.patronDefault);
		properties.setProperty("defaultSeparator", Format.separadorDefault);
		
		//properties.setProperty(Level.debug.toString(),Logger.DEFAULT_NAME_LOGGER+",Output>console");
		properties.setProperty("Logger." + Logger.DEFAULT_NAME_LOGGER + ".level", Level.debug.toString());
		properties.setProperty("Logger." + Logger.DEFAULT_NAME_LOGGER+".output." + OutputType.console.toString()+"0", "lalal");
		
		//properties.setProperty(Level.fatal.toString(),"pepe"+",Output>console,Output>file:log1.txt,Formato>%m");
		
		properties.setProperty("Logger.pepe.level", Level.fatal.toString());
		properties.setProperty("Logger.pepe.output." + OutputType.console.toString()+"0", "lilili");
		properties.setProperty("Logger.pepe.output." + OutputType.file.toString()+"0", "log1.txt");
		properties.setProperty("Logger.pepe.format." + FormatType.Formato.toString()+"0", "%m");
		return properties;
	}

	@Before
	public void setUp() throws Exception {
		// generar aca el "propertiesLog.txt"
		// se ejecuta antes de cada uno de los tests asegurando asi
		// independencia
		Properties properties = generateDefaultTestPropertie();

		File file = new File(NOMBRE_ARCHIVO1_PRUEBA);
		file.createNewFile();

		File fileXml = new File(NOMBRE_ARCHIVO1_PRUEBA + ".xml");
		fileXml.createNewFile();

		OutputStream outXml = new FileOutputStream(fileXml);
		properties.storeToXML(outXml, "");
		outXml.close();

		OutputStream out = new FileOutputStream(file);
		properties.store(out, "");
		out.close();

		outputConsola = new ByteArrayOutputStream();
		viejaConsola = System.out;
	    PrintStream printStream = new PrintStream(outputConsola, true);
	    System.setOut(printStream);
	}

	@After
	public void tearDown() {
		File file = new File(NOMBRE_ARCHIVO1_PRUEBA);
		if (file.exists()) {
			file.delete();
		}
		File fileXml = new File((NOMBRE_ARCHIVO1_PRUEBA + ".xml"));
		if (fileXml.exists()) {
			fileXml.delete();
		}
		File file2 = new File("log1.txt");
		if (file2.exists()) {
			file2.delete();
		}
		System.setOut(viejaConsola);
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
		Log.loadConfigurationFromFile(NOMBRE_ARCHIVO1_PRUEBA+".xml");
		auxLogear("logearConfInXml(): mensaje", Level.fatal, "pepe", true);
	}

	@Test
	public void logearConfInProperties() throws Exception {
		Log.loadConfigurationFromFile(NOMBRE_ARCHIVO1_PRUEBA);
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
		Log.loadConfigurationFromFile(NOMBRE_ARCHIVO1_PRUEBA+".xml");
		noLogearArchivoAux(Level.debug,"pepe");
	}

	@Test
	public void noLogearConfFromPropertiesConDistintoNivelMismoNombre() throws Exception {
		Log.loadConfigurationFromFile(NOMBRE_ARCHIVO1_PRUEBA);
		noLogearArchivoAux(Level.debug,"pepe");
	}
	@Test
	public void noLogearConfFromPropertiesConMismoNivelDistintoNombre() throws Exception {
		Log.loadConfigurationFromFile(NOMBRE_ARCHIVO1_PRUEBA);
		noLogearArchivoAux(Level.fatal,"juan el pastor");
	}
	public void noLogearArchivoAux(Level nivel, String nombreLogger) throws Exception {
		Log.log(nivel, "public void noLogearArchivoAux: mensaje "+nombreLogger+"|"+nivel.toString(), nombreLogger);
		
		File file = new File("log1.txt");
		assertTrue(!file.exists());
		assertTrue(file.length() == 0);
	}
}
