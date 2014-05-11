package ar.fiuba.tecnicas.logging;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class LoggerTest {
	private OutputTester outTester = new OutputTester();
	private Logger logger = new Logger(Niveles.debbug, outTester);

	@Test
	public void log() {
		String message = "PRUEBA";
		logger.logear(message);
		assertEquals(message, outTester.getMessage());
	}
}
