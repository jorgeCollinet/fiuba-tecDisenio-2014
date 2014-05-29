package ar.fiuba.tecnicas.test;

import org.junit.Test;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.*;
import static org.junit.Assert.assertEquals;

/**
 * Clase utilizada para testear el Logger
 * @author Grupo3
 *
 */
public class LoggerTest {
	private OutputMock outputMock = new OutputMock();
	

	@Test
	/**
	 * Test utilizado para testear el logeo de un mensaje debbug en un loger de nivel debbug
	 */
	public void logearDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outputMock, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	/**
	 * Test utilizado para testear el logeo de un mensaje info en un loger de nivel debbug
	 */
	public void logearNivelMensajeInfoNivelLoggerDebbug() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.debbug, outputMock, formato);
		
		logger.logear(Niveles.info, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	/**
	 * Test utilizado para testear el logeo de un mensaje fatal en un loger de nivel warning
	 */
	public void logearNivelMensajeFatalNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.fatal, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	/**
	 * Test utilizado para testear el logeo de un mensaje warning en un loger de nivel warning
	 */
	public void logearNivelMensajeWarningNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.warning, message);
		assertEquals(message, outputMock.getMessage());
	}
	
	@Test
	/**
	 * Test utilizado para testear que no se logee de un mensaje error en un loger de nivel fatal
	 */
	public void noLogearNivelMensajeErrorNivelLoggerFatal() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.fatal, outputMock, formato);
		
		logger.logear(Niveles.error, message);
		System.out.print(outputMock.getMessage());
		assertEquals("", outputMock.getMessage());
	}
	
	@Test
	/**
	 * Test utilizado para testear que no se logee de un mensaje debbug en un loger de nivel warning
	 */
	public void noLogearNivelMensajeDebugNivelLoggerWarning() {
		String message = "TEXTO PRUEBA";
		Formato formato = new Formato("%m", null);
		Logger logger = new Logger(Niveles.warning, outputMock, formato);
		
		logger.logear(Niveles.debbug, message);
		assertEquals("", outputMock.getMessage());
	}
}
