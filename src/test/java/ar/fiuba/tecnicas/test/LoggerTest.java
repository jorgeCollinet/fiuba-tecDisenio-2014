package ar.fiuba.tecnicas.test;

import org.junit.Test;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.*;
import static org.junit.Assert.assertEquals;

public class LoggerTest {
	private OutputTester outTester = new OutputTester();
	

	@Test
	public void testLogearDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outTester, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void testLogearNivelMensajeInfoNivelLoggerDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outTester, formato);
		
		logger.logear(Niveles.info, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void testLogearNivelMensajeFatalNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outTester, formato);
		
		logger.logear(Niveles.fatal, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void testLogearNivelMensajeWarningNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outTester, formato);
		
		logger.logear(Niveles.warning, message);
		assertEquals(message, outTester.getMessage());
	}
	
	@Test
	public void testNoLogearNivelMensajeErrorNivelLoggerFatal() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.fatal, outTester, formato);
		
		logger.logear(Niveles.error, message);
		System.out.print(outTester.getMessage());
		assertEquals("", outTester.getMessage());
	}
	
	@Test
	public void testNoLogearNivelMensajeDebugNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outTester, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals("", outTester.getMessage());
	}
}
