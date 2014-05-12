package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Properties;

public class LoggerBuilder {

	protected LoggerBuilder() {
	}

	public static ArrayList<Logger> generateLoggers(Properties prop) throws Exception {
		ArrayList<Logger> loggers = new ArrayList<>();
		for (Niveles nivel : Niveles.values()) {
			if (prop.containsKey(nivel.toString())) {
				IOutput out = OutputBuilder.generateOutput(prop.getProperty(nivel.toString()), prop);
				loggers.add(new Logger(Niveles.valueOf(nivel.toString()), out));
			}
		}

		return loggers;
	}
}
