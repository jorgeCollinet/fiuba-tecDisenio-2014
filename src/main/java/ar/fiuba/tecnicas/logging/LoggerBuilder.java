package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Properties;

import ar.fiuba.tecnicas.filter.*;
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
				
		/*for (Niveles nivel : Niveles.values()) {
			if (prop.containsKey(nivel.name())) {
				String datosDeNivel = prop.getProperty(nivel.toString());
				IOutput out = OutputBuilder.generateOutput(datosDeNivel);
				Formato format = new Formato(patron, separador);
				Logger logger = new Logger(nivel, out,format);
				loggers.add(logger);
			}
		}*/
		// TODO implementar nuevo parceamiento de cada clase
		for (Niveles nivel : Niveles.values()) {
			if (prop.containsKey(nivel.name())) {
				String datosDeNivel = prop.getProperty(nivel.toString());
				String[] listOfDataLoggers = datosDeNivel.split("\n");
				for(String loggerData : listOfDataLoggers) {					
					IOutput out = OutputBuilder.generateOutput(loggerData);
					ArrayList<IFilter> filters = FilterBuilder.generateFilters(nivel, loggerData);
					// posible formatBuilder ??
					Formato format = new Formato(patron, separador);
					Logger logger = new Logger(nivel, filters, out, format);
					loggers.add(logger);
				}
			}
		}
		
		/*JORGE en el parseo deberias hacer algo asi
		En datos filters le pasas toda la info (nivel, nombreLogger, regex, claseCustom) y adentro hay q parsearlo
		Logger logger = new Logger(nivel, out,format);
		logger.setFilters(FilterBuilder.genetareFilters(datosFilters))
		*/

		return loggers;
	}

	protected static Logger generateLogger(String datosDeEntrada, IOutput out,
			Formato format) {
		// TODO Auto-generated method stub
		String nombre = getNombre(datosDeEntrada);
		Niveles nivel = getNivel(datosDeEntrada);
		return new Logger(nombre,nivel,out,format);
	}

	private static Niveles getNivel(String datosDeEntrada) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param datosDeEntrada
	 * @return nombre si lo encontró , Logger.DEFAULT_NAME_LOGGER si no lo
	 *         encontró
	 */
	private static String getNombre(String datosDeEntrada) {
		// TODO Completar la logica/parceo
		return Logger.DEFAULT_NAME_LOGGER;
	}
}
