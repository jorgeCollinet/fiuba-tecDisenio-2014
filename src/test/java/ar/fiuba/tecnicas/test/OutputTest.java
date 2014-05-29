package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import ar.fiuba.tecnicas.output.OutputConsole;
import ar.fiuba.tecnicas.output.OutputContainer;
import ar.fiuba.tecnicas.output.OutputFile;

/**
 * Clase encargada de testear los distintos tipos de output (OutputContainer, OutputFile, OutputConsole)
 * @author Grupo3
 *
 */
public class OutputTest {	
	
	@Test
	/**
	 * Test encargado de testear el metodo out para OutputFile
	 */
	public void outputFile() throws Exception {
		OutputFile outputFile = new OutputFile("testOutputFile.txt");
		String message = "mensaje de prueba";
		
		File file = new File("testOutputFile.txt");
		file.createNewFile();
		outputFile.out(message);
		assertEquals(message, FileHelper.getLastMessageLogged("testOutputFile.txt"));
		file.delete();
	}
	
	@Test
	/**
	 * Test encargado de testear el metodo out para OutputConsole
	 */
	public void outputConsole() {
		OutputConsole outputConsole = new OutputConsole();
		String message = "mensaje de prueba";
		outputConsole.out(message);		
	}
	
	@Test
	/**
	 * Test encargado de testear el metodo out para OutputContainer
	 */
	public void outputContainer() throws Exception {
		OutputContainer outputContainer = new OutputContainer();
		outputContainer.addOutput(new OutputFile("testOutputContainer.txt"));
		outputContainer.addOutput(new OutputConsole());
		
		File file = new File("testOutputContainer.txt");
		file.createNewFile();
		String message = "mensaje de prueba";
		outputContainer.out(message);
		assertEquals(message, FileHelper.getLastMessageLogged("testOutputContainer.txt"));
		file.delete();
	}
}
