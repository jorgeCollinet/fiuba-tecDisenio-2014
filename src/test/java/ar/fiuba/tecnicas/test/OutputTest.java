package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import ar.fiuba.tecnicas.output.OutputConsole;
import ar.fiuba.tecnicas.output.OutputContainer;
import ar.fiuba.tecnicas.output.OutputFile;
import ar.fiuba.tecnicas.output.OutputCustom;

public class OutputTest {	
	
	@Test
	public void outputCustom() throws Exception {
		OutputCustom outputCustom = OutputCustom.generateOutputCustom("ar.fiuba.tecnicas.test.OutputCustomTest");
		String message = "public void outputCustom() : mensaje de prueba";
		outputCustom.out(message);
	}
	
	@Test
	public void outputFile() throws Exception {
		OutputFile outputFile = new OutputFile("testOutputFile.txt");
		String message = "mensaje de prueba";
		
		outputFile.out(message);
		
		File file = new File("testOutputFile.txt");
		assertTrue(file.exists());
		assertEquals(message, FileHelper.getLastMessageLogged("testOutputFile.txt"));
		file.delete();
	}
	
	@Test
	public void outputConsole() {
		OutputConsole outputConsole = new OutputConsole();
		String message = "public void outputConsole() : mensaje de prueba";
		outputConsole.out(message);		
	}
	
	@Test
	public void outputContainer() throws Exception {
		OutputContainer outputContainer = new OutputContainer();
		outputContainer.addOutput(new OutputFile("testOutputContainer.txt"));
		outputContainer.addOutput(new OutputConsole());
		
		String message = "public void outputContainer(): mensaje de prueba";
		outputContainer.out(message);
		
		File file = new File("testOutputContainer.txt");
		assertTrue(file.exists());
		assertEquals(message, FileHelper.getLastMessageLogged("testOutputContainer.txt"));
		file.delete();
	}
}
