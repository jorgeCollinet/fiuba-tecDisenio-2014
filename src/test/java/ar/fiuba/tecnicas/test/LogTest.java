package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Niveles;
import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputConsole;
import ar.fiuba.tecnicas.output.OutputContainer;
import ar.fiuba.tecnicas.output.OutputFile;

public class LogTest {
	static String NOMBRE_ARCHIVO1_PRUEBA = "propertiesLog.xml";
	@Before
    public void setUp() throws Exception{
		//generar aca el "propertiesLog.txt"
		// se ejecuta antes de cada uno de los tests asegurando asi independencia
		Properties properties = new Properties();
		properties.setProperty("separador", "-");
		properties.setProperty("formato", "%m");
		properties.setProperty(Niveles.debug.toString(),Logger.DEFAULT_NAME_LOGGER+",Output>console");
		properties.setProperty(Niveles.fatal.toString(),Logger.DEFAULT_NAME_LOGGER+",Output>console,Output>file:log1.txt");
		File file = new File(NOMBRE_ARCHIVO1_PRUEBA);
		file.createNewFile();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
		properties.store(out, NOMBRE_ARCHIVO1_PRUEBA);
	}
	@After
	 public void atEnd() {
		File file = new File(NOMBRE_ARCHIVO1_PRUEBA);
		if(file.exists()){
			file.delete();
		}
		File file2 = new File("log1.txt");
		if(file2.exists()){
			file2.delete();
		}
	}
	@Test
	public void loadConfiguration() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("separador", "-");
		properties.setProperty("formato", "%m");
		properties.setProperty(Niveles.fatal.toString(), "LoggerManolo,Output>console");
		properties.setProperty(Niveles.debug.toString(), "LoggerPepe,Output>console,Output>file:log12.txt");

		Log.loadConfiguration(properties);

		ArrayList<Logger> loggers = Log.getLoggers();
		assertEquals(2, loggers.size());

		Logger loggerDebbug = loggers.get(0);
		assertEquals(loggerDebbug.getNivel(), Niveles.debug);
		OutputContainer outputContainerDebbug = (OutputContainer) loggerDebbug.getOutput();
		ArrayList<IOutput> outputsWarning = outputContainerDebbug.getOutputs();
		assertEquals(2, outputsWarning.size());
		IOutput outputConsoleWarning = outputsWarning.get(0);
		assertTrue(outputConsoleWarning instanceof OutputConsole);
		IOutput outputFileWarning = outputsWarning.get(1);
		assertTrue(outputFileWarning instanceof OutputFile);
		
		Logger loggerFatal = loggers.get(1);
		assertEquals(loggerFatal.getNivel(), Niveles.fatal);
		OutputContainer outputContainerFatal = (OutputContainer) loggerFatal.getOutput();
		ArrayList<IOutput> outputsInfo = outputContainerFatal.getOutputs();
		assertEquals(1, outputsInfo.size());
		IOutput outputConsoleInfo = outputsInfo.get(0);
		assertTrue(outputConsoleInfo instanceof OutputConsole);
	}
	
	@Test
	public void logear() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(NOMBRE_ARCHIVO1_PRUEBA)));
		Log.loadConfiguration(prop);
		
		String messageFatal = "public void logear(): mensaje fatal";
		Log.log(Niveles.fatal, messageFatal);
		
		File file = new File("log1.txt");
		assertTrue(file.exists());
		assertEquals(messageFatal, FileHelper.getLastMessageLogged("log1.txt"));
		file.delete();
	}
	
	@Test
	public void noLogear() throws Exception {
		
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(NOMBRE_ARCHIVO1_PRUEBA)));
		Log.loadConfiguration(prop);
		
		String messageDebbug = "mensaje debbug";
		Log.log(Niveles.debug, messageDebbug);
		
		File file = new File("log1.txt");
		assertTrue(!file.exists());
		assertTrue(file.length() == 0);
		file.delete();
	}
}
