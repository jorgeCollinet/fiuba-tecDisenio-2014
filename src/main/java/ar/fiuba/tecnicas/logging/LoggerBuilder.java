package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Properties;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputBuilder;
/**
 * Clase encargada de la construccion de los loggers a partir de una instancia de la clase Properties
 * @author Grupo3
 *
 */
public class LoggerBuilder {

	protected LoggerBuilder() {
	}

	public static ArrayList<Logger> generateLoggers(Properties prop) throws Exception {
		ArrayList<Logger> loggers = new ArrayList<>();
		String patron = prop.getProperty("formato",null);
		String separador = prop.getProperty("separador",null);
		
		for (Niveles nivel : Niveles.values()) {
			if (prop.containsKey(nivel.name())) {
				String datosDeNivel = prop.getProperty(nivel.toString());
				IOutput out = OutputBuilder.generateOutput(datosDeNivel);
				Formato format = new Formato(patron, separador);
				Logger logger = new Logger(nivel, out,format);
				loggers.add(logger);
			}
		}

		return loggers;
	}
}
