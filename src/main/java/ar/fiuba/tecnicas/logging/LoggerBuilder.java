package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Properties;

import ar.fiuba.tecnicas.filter.*;
import ar.fiuba.tecnicas.formato.FormatBuilder;
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
		String patronDefault = prop.getProperty("FormatoDefault",null);
		String separador = prop.getProperty("Separador",null);
		
		for (Niveles nivel : Niveles.values()) {
			if (prop.containsKey(nivel.name())) {
				String datosDeNivel = prop.getProperty(nivel.toString());
				String[] listOfDataLoggers = datosDeNivel.split("\n");
				for(String loggerData : listOfDataLoggers) {
					String nombreLogger = loggerData.split(",")[0];
					IOutput out = OutputBuilder.generateOutput(loggerData);
					ArrayList<IFilter> filters = FilterBuilder.generateFilters(nivel, loggerData);
					Formato format = FormatBuilder.generateFormats(loggerData,patronDefault, separador);
					Logger logger = new Logger(nombreLogger, nivel, filters, out, format);
					loggers.add(logger);
				}
			}
		}
		return loggers;
	}
}
