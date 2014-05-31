package ar.fiuba.tecnicas.test;

import org.junit.Test;

import ar.fiuba.tecnicas.filter.*;
import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Niveles;
import static org.junit.Assert.assertEquals;

public class LoggerTest {
	private OutputMock outputMock = new OutputMock();
	

	@Test
	public void logearDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outputMock, formato);
		
		FilterNombre filterNombre = new FilterNombre("logger1");
		FilterNivel filterNivel = new FilterNivel(Niveles.debbug);
		FilterRegex filterRegex = new FilterRegex("TEXTO.*");
		
		logger.addFilter(filterNombre);
		logger.addFilter(filterNivel);
		logger.addFilter(filterRegex);
		
		logger.logear(Niveles.debbug, message, "logger1");
		assertEquals(message, outputMock.getMessage());
	}
	
	/*@Test
	public void logearNivelMensajeInfoNivelLoggerTrace() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.trace, outputMock, formato);
		
		logger.logear(Niveles.trace, message);
		assertEquals(message, outputMock.getMessage());
	}
	@Test
	public void logearNivelMensajeInfoNivelLoggerDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outputMock, formato);
		
		logger.logear(Niveles.info, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void logearNivelMensajeFatalNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.fatal, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void logearNivelMensajeWarningNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.warning, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void noLogearNivelMensajeErrorNivelLoggerFatal() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.fatal, outputMock, formato);
		
		logger.logear(Niveles.error, message);
		System.out.print(outputMock.getMessage());
		assertEquals("", outputMock.getMessage());
	}
	
	@Test
	public void noLogearNivelMensajeDebugNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals("", outputMock.getMessage());
	}*/
}
