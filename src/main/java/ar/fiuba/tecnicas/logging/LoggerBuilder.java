package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Enumeration;
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

		// TODO implementar parceo para 2da entrega
		/*Enumeration<?> enume = prop.propertyNames();
		while (enume.hasMoreElements()){
			String key = (String) enume.nextElement();
			String datosDeEntrada = prop.getProperty(key);
			IOutput out = OutputBuilder.generateOutput(datosDeEntrada);
			Formato format = new Formato(patron, separador);
			Logger logger = generateLogger(datosDeEntrada,out,format);
			//Logger logger = new Logger(nivel, out,format);
			loggers.add(logger);
		}*/
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
