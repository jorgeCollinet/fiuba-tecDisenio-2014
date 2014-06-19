package ar.fiuba.tecnicas.logging;

import java.io.File;

public class LogStartupLoader {

	private LogStartupLoader() {

	}

	private static final String propertiesFilename = "logger-config.properties";
	private static final String xmlFilename = "logger-config.xml";
	static // Carga automatica de loggers
	{
		File file = new File(propertiesFilename);
		if (file.exists() && !file.isDirectory()) {
			try {
				Log.loadConfigurationFromFile(propertiesFilename);
			} catch (Exception e) {
				System.out.println("Error al cargar " + propertiesFilename);
			}
		}
		file = new File(xmlFilename);
		if (Log.loggers != null && file.exists() && !file.isDirectory()) {
			try {
				Log.loadConfigurationFromFile(xmlFilename);
			} catch (Exception e) {
				System.out.println("Error al cargar " + xmlFilename);
			}
		}
		if (Log.loggers == null) {
			try {
				Log.loadConfigurationDefault();
			} catch (Exception e) {
				System.out.println("Error al cargar el logger por defecto. "
						+ e.getMessage());
			}
		}
	}
}
