package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Niveles;
import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputConsole;
import ar.fiuba.tecnicas.output.OutputContainer;
import ar.fiuba.tecnicas.output.OutputFile;

public class LogTest {

	@Test
	public void testLoadConfiguration() throws Exception {
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
	
	@Test
	public void testLogear() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("propertiesLog.txt")));
		Log.loadConfiguration(prop);
		
		String messageFatal = "mensaje fatal";
		Log.log(Niveles.fatal, messageFatal);
		
		BufferedReader file = new BufferedReader(new FileReader("log1.txt"));
	    try {
	    	String sCurrentLine;
	        String lastLine = "";

	        while ((sCurrentLine = file.readLine()) != null){
	            lastLine = sCurrentLine;
	        }

	        assertEquals(messageFatal, lastLine);
	    } finally {
	        file.close();
	    }
	}
	
	@Test
	public void testNoLogear() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("propertiesLog.txt")));
		Log.loadConfiguration(prop);
		
		String messageDebbug = "mensaje debbug";
		Log.log(Niveles.debbug, messageDebbug);
		
		BufferedReader file = new BufferedReader(new FileReader("log1.txt"));
	    try {
	    	String sCurrentLine;
	        String lastLine = "";

	        while ((sCurrentLine = file.readLine()) != null){
	            lastLine = sCurrentLine;
	        }
	    	assertNotEquals(messageDebbug, lastLine);
	    } finally {
	        file.close();
	    }
	}
}
