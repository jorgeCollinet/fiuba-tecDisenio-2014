package ar.fiuba.tecnicas.logging;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class LoggerBuilder {

	protected LoggerBuilder() {
	}

	public static ArrayList<Logger> generateLoggers (String nombrePropertiesLoggers)
			throws Exception {
		
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(nombrePropertiesLoggers);
		prop.load(in);
		
		ArrayList<Logger> loggers = new ArrayList<>();
		Set<String> LoggerList = prop.stringPropertyNames();
		Iterator<String> it = LoggerList.iterator();
		while (it.hasNext()) {
			IOutput out = OutputBuilder.generateOutput(OutputType.valueOf(prop
					.getProperty(it.toString())));
			loggers.add(new Logger(Niveles.valueOf(it.toString()), out));
		}
		return loggers;
	}
}
