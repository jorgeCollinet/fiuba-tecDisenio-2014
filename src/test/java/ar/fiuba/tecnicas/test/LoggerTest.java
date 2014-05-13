package ar.fiuba.tecnicas.test;

import org.junit.Test;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.*;
import static org.junit.Assert.assertEquals;

public class LoggerTest {
	private OutputTester outTester = new OutputTester();
	

	@Test
	public void log() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outTester, formato);
		
		logger.logear(Niveles.error, message);
		assertEquals(message, outTester.getMessage());
	}
}
