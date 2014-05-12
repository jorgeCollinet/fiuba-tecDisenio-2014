package ar.fiuba.tecnicas.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Grupo3
 */
public class Log {
	protected static ArrayList<Logger> loggers;
	protected static InputStream inStream;
	protected static Properties prop = new Properties();
	protected static String ConfigurationFileName;
	protected static String logFileName;
	protected static String propertiesLoggersFileName;
	
	protected Log() { }
	
	static protected void createEmptyFile(String fileName) throws IOException, Exception {
		File file = new File(fileName);
		if( !file.isFile() && !file.createNewFile()){
			throw new Exception("no se pudo crear el archivo:"+fileName);
		}
	}
	/**
	 * Metodo encargado de cargar la configuracion del log
	 * 
	 * @param fileName
	 * @throws Exception 
	 */
	static protected void loadConfiguration(String fileName) throws Exception {
		createEmptyFile(fileName);
		ConfigurationFileName = fileName;
		Log.inStream = new FileInputStream(ConfigurationFileName);
		prop.load(inStream);
		
		logFileName = prop.getProperty("nombreLogFile", "log.txt");
		createEmptyFile(logFileName);
		propertiesLoggersFileName = prop.getProperty("nombrePropertiesLoggers", "loggersProperties.txt");
		createEmptyFile(propertiesLoggersFileName);
		// FIXME pasar a una forma mas linda, esta llamada no deveria estar aca
		OutputBuilder.setOutputFileName(logFileName);
		
		loggers = LoggerBuilder.generateLoggers(propertiesLoggersFileName);
	}
	
	static protected void saveConfiguration() throws IOException{
		FileOutputStream fileOut = new FileOutputStream(ConfigurationFileName);
		prop.store(fileOut, "no comment");
		
	}

	/**
	 * 
	 * @param nivel
	 * @param message
	 */
	static void log(Enum<Niveles> nivel, String message) {
		for (Logger logger : loggers) {
			logger.logear(message);
		}

	}

}
