package ar.fiuba.tecnicas.test;

import org.junit.Test;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.*;
import static org.junit.Assert.assertEquals;

public class LoggerTest {
	private OutputTester outTester = new OutputTester();
	

	@Test
	public void logearDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outTester, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void logearNivelMensajeInfoNivelLoggerDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outTester, formato);
		
		logger.logear(Niveles.info, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void logearNivelMensajeFatalNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outTester, formato);
		
		logger.logear(Niveles.fatal, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void logearNivelMensajeWarningNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outTester, formato);
		
		logger.logear(Niveles.warning, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void noLogearNivelMensajeErrorNivelLoggerFatal() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.fatal, outTester, formato);
		
		logger.logear(Niveles.error, message);
		System.out.print(outTester.getMessage());
		assertEquals("", outTester.getMessage());
	}
	
	@Test
	public void noLogearNivelMensajeDebugNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outTester, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals("", outTester.getMessage());
	}
}
