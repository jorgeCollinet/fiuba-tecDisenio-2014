package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

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
	
	@Before
    public void setUp() throws Exception{
		// TODO generar aca el "propertiesLog.txt"
		// se ejecuta antes de cada uno de los tests asegurando asi independencia
		
	}
	
	@Test
	public void loadConfiguration() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("separador", "-");
		properties.setProperty("formato", "%m");
		properties.setProperty("fatal", "LoggerPepe,Output>console");
		properties.setProperty("debbug", "LoggerManolo,Output>console,Output>file:log1.txt");
		
		Log.loadConfiguration(properties);

		ArrayList<Logger> loggers = Log.getLoggers();
		assertEquals(2, loggers.size());

		Logger loggerDebbug = loggers.get(0);
		assertEquals(loggerDebbug.getNivel(), Niveles.debbug);
		OutputContainer outputContainerWarning = (OutputContainer) loggerDebbug.getOutput();
		ArrayList<IOutput> outputsWarning = outputContainerWarning.getOutputs();
		assertEquals(2, outputsWarning.size());
		IOutput outputConsoleWarning = outputsWarning.get(0);
		assertTrue(outputConsoleWarning instanceof OutputConsole);
		IOutput outputFileWarning = outputsWarning.get(1);
		assertTrue(outputFileWarning instanceof OutputFile);
		
		Logger loggerFatal = loggers.get(1);
		assertEquals(loggerFatal.getNivel(), Niveles.fatal);
		OutputContainer outputContainerInfo = (OutputContainer) loggerFatal.getOutput();
		ArrayList<IOutput> outputsInfo = outputContainerInfo.getOutputs();
		assertEquals(1, outputsInfo.size());
		IOutput outputConsoleInfo = outputsInfo.get(0);
		assertTrue(outputConsoleInfo instanceof OutputConsole);
	}
	// FIXME no tiene que usar el archivo previamente existente
	@Test
	public void loadConfigurationByFile() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("propertiesLog.txt")));
		Log.loadConfiguration(prop);

		ArrayList<Logger> loggers = Log.getLoggers();
		assertEquals(2, loggers.size());

		Logger loggerInfo = loggers.get(0);
		assertEquals(loggerInfo.getNivel(), Niveles.info);
		OutputContainer outputContainerInfo = (OutputContainer) loggerInfo.getOutput();
		ArrayList<IOutput> outputsInfo = outputContainerInfo.getOutputs();
		assertEquals(1, outputsInfo.size());
		IOutput outputConsoleInfo = outputsInfo.get(0);
		assertTrue(outputConsoleInfo instanceof OutputConsole);
		
		Logger loggerWarning = loggers.get(1);
		assertEquals(loggerWarning.getNivel(), Niveles.warning);
		OutputContainer outputContainerWarning = (OutputContainer) loggerWarning.getOutput();
		ArrayList<IOutput> outputsWarning = outputContainerWarning.getOutputs();
		assertEquals(2, outputsWarning.size());
		IOutput outputConsoleWarning = outputsWarning.get(0);
		assertTrue(outputConsoleWarning instanceof OutputConsole);
		IOutput outputFileWarning = outputsWarning.get(1);
		assertTrue(outputFileWarning instanceof OutputFile);
	}
	// FIXME no tiene que usar el archivo previamente existente
	@Test
	public void logear() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("propertiesLog.txt")));
		Log.loadConfiguration(prop);
		
		File file = new File("log1.txt");
		file.createNewFile();
		String messageFatal = "mensaje fatal";
		Log.log(Niveles.fatal, messageFatal);
		
		assertEquals(messageFatal, FileHelper.getLastMessageLogged("log1.txt"));
		file.delete();
	}
	
	//FIXME test mal hecho, no puede cargar la configuracion de un archivo preexistente
	//se tendria que generar en la misma prueba
	@Test
	public void noLogear() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("propertiesLog.txt")));
		Log.loadConfiguration(prop);
		
		File file = new File("log1.txt");
		file.createNewFile();
		String messageDebbug = "mensaje debbug";
		Log.log(Niveles.debbug, messageDebbug);
		
		assertTrue(file.length() == 0);
		file.delete();
	}
}
