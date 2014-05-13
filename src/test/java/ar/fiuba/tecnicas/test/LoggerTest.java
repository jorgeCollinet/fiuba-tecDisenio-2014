package ar.fiuba.tecnicas.test;

import org.junit.Test;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.*;
import static org.junit.Assert.assertEquals;

public class LoggerTest {
	private OutputTester outTester = new OutputTester();
	

	@Test
	public void log() {
		String message = "PRUEBA";
		String formato="";
		String separador=",";
		Formato format = new Formato(formato, separador);
		Logger logger = new Logger(Niveles.debbug, outTester, format);
		
		logger.logear(Niveles.error, message);
		assertEquals(message, outTester.getMessage());
	}
}
