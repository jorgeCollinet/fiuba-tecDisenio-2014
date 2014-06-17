package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import ar.fiuba.tecnicas.filter.*;
import ar.fiuba.tecnicas.format.FormatBuilder;
import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.output.IOutput;
import ar.fiuba.tecnicas.output.OutputBuilder;
/**
 * Clase encargada de la construccion de los loggers
 * @author Grupo3
 *
 */
public class LoggerBuilder {

	public static ArrayList<Logger> generateLoggers(ArrayList<LoggerConfig> loggerConfigs) throws Exception {
		ArrayList<Logger> loggers = new ArrayList<>();
		for(LoggerConfig loggerConf : loggerConfigs){
			IOutput out = OutputBuilder.generateOutput(loggerConf.getOutputs());
			ArrayList<IFilter> filters = FilterBuilder.generateFilters(loggerConf.getName(), loggerConf.getLevel(), loggerConf.getFilters());
			Format format = FormatBuilder.generateFormat(loggerConf.getFormat(),loggerConf.getDefaultFormat(), loggerConf.getSeparator());
			Logger logger = new Logger(loggerConf.getName(), loggerConf.getLevel(), filters, out, format);
			loggers.add(logger);
		}
		return loggers;		
	}
}
