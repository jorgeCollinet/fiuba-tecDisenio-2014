package ar.fiuba.tecnicas.test;

import org.junit.Test;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.*;
import static org.junit.Assert.assertEquals;

public class LoggerTest {
	private OutputMock outputMock = new OutputMock();
	

	@Test
	public void testLogearDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outputMock, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void testLogearNivelMensajeInfoNivelLoggerDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outputMock, formato);
		
		logger.logear(Niveles.info, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void testLogearNivelMensajeFatalNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.fatal, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void testLogearNivelMensajeWarningNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.warning, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void testNoLogearNivelMensajeErrorNivelLoggerFatal() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.fatal, outputMock, formato);
		
		logger.logear(Niveles.error, message);
		System.out.print(outputMock.getMessage());
		assertEquals("", outputMock.getMessage());
	}
	
	@Test
	public void testNoLogearNivelMensajeDebugNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals("", outputMock.getMessage());
	}
}
