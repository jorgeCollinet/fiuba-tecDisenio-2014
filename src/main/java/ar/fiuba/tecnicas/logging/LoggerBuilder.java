package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.List;

import ar.fiuba.tecnicas.filter.*;
import ar.fiuba.tecnicas.format.FormatBuilder;
import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputBuilder;
import ar.fiuba.tecnicas.output.OutputType;

/**
 * Clase encargada de la construccion de los loggers
 * 
 * @author Grupo3
 * 
 */
public class LoggerBuilder {

	/**
	 * Carga los logers a partir de una lista de configuraciones
	 * 
	 * @param loggerConfigs
	 *            Lista de configuraciones
	 * @return La lista de loggers
	 * @throws Exception
	 */
	public static List<Logger> generateLoggers(List<LoggerConfig> loggerConfigs)
			throws Exception {
		ArrayList<Logger> loggers = new ArrayList<>();
		for (LoggerConfig loggerConf : loggerConfigs) {
			IOutput out = OutputBuilder.generateOutput(
					loggerConf.getOutputTypes(), loggerConf.getOutputs());
			List<IFilter> filters = FilterBuilder.generateFilters(
					loggerConf.getName(), loggerConf.getLevel(),
					loggerConf.getFilters(), loggerConf.getFilterTypes());
			Format format = FormatBuilder.generateFormat(
					loggerConf.getFormatType(), loggerConf.getFormat(),
					loggerConf.getDefaultFormat(), loggerConf.getSeparator());
			Logger logger = new Logger(loggerConf.getName(),
					loggerConf.getLevel(), filters, out, format);
			loggers.add(logger);
		}
		return loggers;
	}

	/**
	 * Devuelve un logger por defecto
	 */
	public static List<Logger> generateDefaultLogger() throws Exception {
		LoggerConfig config = new LoggerConfig();
		config.setName("defaultLogger");
		config.setLevel(Level.info);
		config.addOutput(null, OutputType.console);
		List<LoggerConfig> loggerConfigs = new ArrayList<LoggerConfig>();
		loggerConfigs.add(config);
		return generateLoggers(loggerConfigs);
	}
}
