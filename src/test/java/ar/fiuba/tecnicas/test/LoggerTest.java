package ar.fiuba.tecnicas.test;

import java.util.ArrayList;

import org.junit.Test;

import ar.fiuba.tecnicas.filter.*;
import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Level;
import static org.junit.Assert.assertEquals;


public class LoggerTest {
	private OutputMock outputMock = new OutputMock();
	

	@Test
	public void logearAplicandoFiltros() throws Exception {
		String message = "public void logearAplicandoFiltros(): TEXTO PRUEBA";
		Format formato = new Format("%m", null);
		
		ArrayList <IFilter> filters = new ArrayList<IFilter>();
		FilterNombre filterNombre = new FilterNombre("logger1");
		FilterNivel filterNivel = new FilterNivel(Level.debug);
		FilterRegex filterRegex = new FilterRegex(".*TEXTO.*");
		FilterCustom filterCustom = FilterCustom.generateFilterCustom("ar.fiuba.tecnicas.test.FilterCustomMock");
		
		filters.add(filterNombre);
		filters.add(filterNivel);
		filters.add(filterRegex);
		filters.add(filterCustom);
		
		Logger logger = new Logger(Logger.DEFAULT_NAME_LOGGER, Level.debug,filters, outputMock, formato);
		
		logger.logear(Level.debug, message, "logger1");
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void logearNivelMensajeInfoNivelLoggerTrace() {
		String message = "public void logearNivelMensajeInfoNivelLoggerTrace(): TEXTO PRUEBA\n";
		Format formato = new Format("%m", null);
		Logger logger = new Logger(Level.trace, new ArrayList<IFilter>(), outputMock, formato);
		
		logger.logear(Level.trace, message);
		assertEquals(message, outputMock.getMessage());
	}
	@Test
	public void logearNivelMensajeInfoNivelLoggerDebbug() {
		String message = "public void logearNivelMensajeInfoNivelLoggerDebbug(): TEXTO PRUEBA\n";
		Format formato = new Format("%m", null);
		Logger logger = new Logger(Level.debug, new ArrayList<IFilter>(), outputMock, formato);
		
		logger.logear(Level.info, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void logearNivelMensajeFatalNivelLoggerWarning() {
		String message = "public void logearNivelMensajeFatalNivelLoggerWarning(): TEXTO PRUEBA";
		Format formato = new Format("%m", null);
		Logger logger = new Logger(Level.warning, new ArrayList<IFilter>(), outputMock, formato);
		
		logger.logear(Level.fatal, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void logearNivelMensajeWarningNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Format formato = new Format("%m", null);
		Logger logger = new Logger(Level.warning, new ArrayList<IFilter>(),outputMock, formato);
		
		logger.logear(Level.warning, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void noLogearNivelMensajeErrorNivelLoggerFatal() {
		String message = "noLogearNivelMensajeErrorNivelLoggerFatal()";
		Format formato = new Format("%m", null);
		Logger logger = new Logger(Level.fatal,new ArrayList<IFilter>(), outputMock, formato);
		
		logger.logear(Level.error, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	public void noLogearNivelMensajeDebugNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Format formato = new Format("%m", null);
		Logger logger = new Logger(Level.warning,new ArrayList<IFilter>() ,outputMock, formato);
		
		logger.logear(Level.debug, message);
		assertEquals("TEXTO PRUEBA", outputMock.getMessage());
	}
}
