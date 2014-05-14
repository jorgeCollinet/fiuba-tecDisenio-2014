package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputConsole;
import ar.fiuba.tecnicas.output.OutputContainer;
import ar.fiuba.tecnicas.output.OutputFile;

public class OutputTester implements IOutput {

	protected String buffer;

	public OutputTester() {
		buffer = "";
	}

	@Override
	public void out(String mensaje) {
		buffer = mensaje;
	}

	public String getMessage() {
		return buffer;
	}
	
	@Test
	public void testOutputFile() throws Exception {
		OutputFile outputFile = new OutputFile("testOutputFile.txt");
		String message = "mensaje de prueba";
		outputFile.out(message);
		
		BufferedReader file = new BufferedReader(new FileReader("testOutputFile.txt"));
	    try {
	        String line = file.readLine();
	        line = line.replace("\"", "");
	        assertEquals(message, line);
	    } finally {
	        file.close();
	    }
	}
	
	@Test
	public void testOutputConsole() {
		OutputConsole outputConsole = new OutputConsole();
		String message = "mensaje de prueba";
		outputConsole.out(message);		
	}
	
	@Test
	public void testOutputContainer() throws Exception {
		OutputContainer outputContainer = new OutputContainer();
		outputContainer.addOutput(new OutputFile("testOutputContainer.txt"));
		outputContainer.addOutput(new OutputConsole());
		
		String message = "mensaje de pruebabababab";
		outputContainer.out(message);
		
		BufferedReader file = new BufferedReader(new FileReader("testOutputContainer.txt"));
	    try {
	        String line = file.readLine();
	        line = line.replace("\"", "");
	        assertEquals(message, line);
	    } finally {
	        file.close();
	    }
	}
}
