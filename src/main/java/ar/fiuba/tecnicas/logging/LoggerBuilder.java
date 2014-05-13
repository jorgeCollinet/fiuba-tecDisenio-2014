package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Properties;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputBuilder;

public class LoggerBuilder {

	protected LoggerBuilder() {
	}

	public static ArrayList<Logger> generateLoggers(Properties prop) throws Exception {
		ArrayList<Logger> loggers = new ArrayList<>();
		// TODO ver que hacer con el patron default
		String patron = prop.getProperty("patron");
		// TODO cer que hacer con el separador default
		String separador = prop.getProperty("separador");
		
		for (Niveles nivel : Niveles.values()) {
			if (prop.containsKey(nivel)) {
				String datosDeNivel = prop.getProperty(nivel.toString());
				IOutput out = OutputBuilder.generateOutput(datosDeNivel, prop);
				Formato format = new Formato(patron, separador);
				Logger logger = new Logger(nivel, out,format);
				loggers.add(logger);
			}
		}

		return loggers;
	}
}
