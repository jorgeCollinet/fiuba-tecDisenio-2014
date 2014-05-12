package ar.fiuba.tecnicas.logging;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class LoggerBuilder {

	protected LoggerBuilder() {
	}

	public static ArrayList<Logger> generateLoggers(
			String nombrePropertiesLoggers) throws Exception {

		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(nombrePropertiesLoggers);
		prop.load(in);

		ArrayList<Logger> loggers = new ArrayList<>();
		for (Niveles nivel : Niveles.values()) {
			if (prop.containsKey(nivel.toString())) {
				IOutput out = OutputBuilder.generateOutput(prop.getProperty(nivel.toString()));
				loggers.add(new Logger(Niveles.valueOf(nivel.toString()), out));
			}
		}

		return loggers;
	}
}
