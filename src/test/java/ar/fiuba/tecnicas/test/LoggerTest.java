package ar.fiuba.tecnicas.test;

import java.util.ArrayList;

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
		String message = "public void logearDebbug(): TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		
		ArrayList <IFilter> filters = new ArrayList<IFilter>();
		FilterNombre filterNombre = new FilterNombre("logger1");
		FilterNivel filterNivel = new FilterNivel(Niveles.debug);
		FilterRegex filterRegex = new FilterRegex(".*TEXTO.*");
		FilterCustom filterCustom = new FilterCustom("ar.fiuba.tecnicas.filter.FilterCustomHorario");
		
		filters.add(filterNombre);
		filters.add(filterNivel);
		filters.add(filterRegex);
		filters.add(filterCustom);
		
		Logger logger = new Logger(Niveles.debug,filters, outputMock, formato);
		
		logger.logear(Niveles.debug, message, "logger1");
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void logearNivelMensajeInfoNivelLoggerTrace() {
		String message = "public void logearNivelMensajeInfoNivelLoggerTrace(): TEXTO PRUEBA\n";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.trace, outputMock, formato);
		
		logger.logear(Niveles.trace, message);
		assertEquals(message, outputMock.getMessage());
	}
	@Test
	public void logearNivelMensajeInfoNivelLoggerDebbug() {
		String message = "public void logearNivelMensajeInfoNivelLoggerDebbug(): TEXTO PRUEBA\n";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debug, outputMock, formato);
		
		logger.logear(Niveles.info, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void logearNivelMensajeFatalNivelLoggerWarning() {
		String message = "public void logearNivelMensajeFatalNivelLoggerWarning(): TEXTO PRUEBA";
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
		assertEquals("TEXTO PRUEBA", outputMock.getMessage());
	}
	
	@Test
	public void noLogearNivelMensajeDebugNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.debug, message);
		assertEquals("TEXTO PRUEBA", outputMock.getMessage());
	}
}
