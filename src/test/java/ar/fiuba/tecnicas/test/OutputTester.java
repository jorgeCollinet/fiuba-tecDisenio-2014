package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.*;
import ar.fiuba.tecnicas.output.IOutput;

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
    public void outputTesterTest() {
        OutputTester outputTester = new OutputTester();
        String message = "testMessage";
        
        outputTester.out(message);   
		assertEquals(message, outputTester.getMessage());
    }

}
