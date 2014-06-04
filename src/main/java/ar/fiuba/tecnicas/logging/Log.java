package ar.fiuba.tecnicas.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.lang.StackTraceElement;

/**
 * Clase encargada de recibir todos los mensajes de log y redistribuirlo a los distintos loggers
 * @author Grupo3
 */
public class Log {
	
	protected static ArrayList<Logger> loggers = new ArrayList<Logger>();
	protected static InputStream inStream;
	protected static Properties configuration;
	protected static String logFileName;
	protected static String propertiesLoggersFileName;
	
	protected Log() { }
	
	/**
	 * Metodo encargado de cargar la configuracion del log
	 * 
	 * @param configuration
	 * @throws Exception 
	 */
	public static void loadConfiguration(Properties configuration) throws Exception {
		Log.configuration = configuration;
		loggers = LoggerBuilder.generateLoggers(configuration);
	}
	
	/**
	 * Metodo encargado de cargar la configuracion del log desde un archivo
	 * 
	 * @param fileName
	 * @throws Exception 
	 */
	public static void loadConfigurationFromFile(String fileName)
			throws Exception {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(new File(fileName));
		if (fileName.endsWith("xml")) {
			prop.loadFromXML(file);
		} else {
			prop.load(file);
		}
		Log.loadConfiguration(prop);
	}
	
	/**
	 * 
	 * @param nivel
	 * @param message
	 */
	public static void log(Niveles nivel, String message) {
		String nombreLogger = Logger.DEFAULT_NAME_LOGGER;
		Log.log(nivel, message, nombreLogger);

	}

	/**
	 * Logea un mensaje
	 * @param nivel			Nivel del mensaje
	 * @param message		Mensaje
	 * @param nombreLogger	Nombre del logger
	 */
	public static void log(Niveles nivel, String message, String nombreLogger) {
		for (Logger logger : loggers) {
			logger.logear(nivel, message, nombreLogger);
		}
	}
	
	/**
	 * Logea una excepción
	 * @param nivel			Nivel del mensaje
	 * @param throwable		Excepción
	 * @param nombreLogger	Nombre del logger
	 */
	public static void log(Niveles nivel, Throwable throwable, String nombreLogger) {
		for (Logger logger : loggers) {
			logger.logear(nivel, throwableToString(throwable), nombreLogger);
		}
	}
	
	/**
	 * Logea un mensaje y una excepción 
	 * @param nivel			Nivel del mensaje
	 * @parame message		Mensaje
	 * @param throwable		Excepción
	 * @param nombreLogger	Nombre del logger
	 */
	public static void log(Niveles nivel, String message, Throwable throwable, String nombreLogger) {
		for (Logger logger : loggers) {
			logger.logear(nivel, message+":"+throwableToString(throwable), nombreLogger);
		}
	}
	
	private static String throwableToString(Throwable throwable)
	{
		String result = "";
		if (throwable.getMessage() != null) result += throwable.getMessage()+":\n";
		result += "Stacktrace:\n";
		for (StackTraceElement e : throwable.getStackTrace())
		{
			result += "At line "+e.getLineNumber()+" in "+e.getMethodName()
					+" in file " + e.getFileName() +"\n";
		}
		return result;
	}
	
	/**
	 * Verifica si un un logger con un determinado nombre existe
	 * @param nombre	Nombre del logger
	 * @return			True si existe, false si no existe.
	 */
	public static boolean loggerExists(String nombre)
	{
		for (Logger logger : loggers)
			if (nombre == logger.nombre) return true;
		return false;
	}
	
	/**
	 * Dado un nombre de logger, devuelve su nivel
	 * @param nombre	Nombre del logger
	 * @return			El nivel, o una excepción si no existe el logger
	 */
	public static Niveles getNivelLogger(String nombre)
	{
		for (Logger logger : loggers)
			if (nombre == logger.nombre) return logger.nivel;
		throw new IllegalArgumentException();
	}
	
	public static ArrayList<Logger> getLoggers(){
		return loggers;
	}
}
