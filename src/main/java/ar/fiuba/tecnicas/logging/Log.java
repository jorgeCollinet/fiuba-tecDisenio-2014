package ar.fiuba.tecnicas.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

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
	public static void loadConfigurationFromFile(String fileName) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(fileName)));
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
	 * 
	 * @param nivel
	 * @param message
	 */
	public static void log(Niveles nivel, String message,String nombreLogger) {
		for (Logger logger : loggers) {
			logger.logear(nivel, message, nombreLogger);
		}

	}
	
	public static ArrayList<Logger> getLoggers(){
		return loggers;
	}
}
