package ar.fiuba.tecnicas.logging;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import ar.fiuba.tecnicas.filter.FilterType;
import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.output.OutputType;

public class PropertiesLoader {

	public static ArrayList<LoggerConfig> loadConfiguration(String path) throws Exception {
		ArrayList<LoggerConfig> loggersConf = new ArrayList<>();
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(new File(path));
		prop.load(file);

		String[] loggerNames = prop.getProperty("rootLoggers").split(",");
		for (String loggerName : loggerNames) {
			LoggerConfig loggerConf = new LoggerConfig();
			loggerConf.setName(loggerName);
			loggerConf.setDefaultFormat(prop.getProperty("defaultFormat"));
			
			String initialKey = "Logger." + loggerName + ".";
			loggerConf.setSeparator(prop.getProperty(initialKey + "separator", prop.getProperty("defaultSeparator")));			
			
			loggerConf.setLevel(Level.valueOf(prop.getProperty(initialKey+ "level")));
			if(loggerConf.getLevel() == null){
				throw new Exception("logger:" + loggerName + " no tiene un nivel configurado");
			}

			loadFilters(loggerConf, prop, initialKey + "filter.");
			loadOutputs(loggerConf, prop, initialKey + "output.");
			loadFormats(loggerConf, prop, initialKey + "format.");

			loggersConf.add(loggerConf);
		}
		return loggersConf;
	}

	private static void loadFormats(LoggerConfig loggerConf, Properties prop, String initialKey) {
		for(FormatType formatType: FormatType.values()) {
			auxFormats(initialKey, formatType.toString(), loggerConf, prop);
		}
	}

	private static void loadOutputs(LoggerConfig loggerConf, Properties prop, String initialKey) {
		for(OutputType outputType: OutputType.values()){
			auxOutput(initialKey, outputType.toString(), loggerConf, prop);
		}
	}
	
	private static void loadFilters(LoggerConfig loggerConf, Properties prop, String initialKey) {
		for(FilterType filterType: FilterType.values() ){
			auxFilters(initialKey, filterType.toString(), loggerConf, prop);
		}

	}
	
	private static void auxOutput(String initialKey, String diferentialKey, LoggerConfig loggerConf, Properties prop) {
		int count = 0;
		String element = prop.getProperty(initialKey + diferentialKey + count);
		//System.out.println("chequea: "+initialKey + diferentialKey + count);
		//System.out.println("elemento: "+element);
		while (element != null) {
			loggerConf.addOutputType(diferentialKey);
			loggerConf.addOutput(element);
			//System.out.println("entro: "+loggerConf.getOutputs());
			count++;
			element = prop.getProperty(initialKey + diferentialKey + count);
		}
	}

	private static void auxFormats(String initialKey, String diferentialKey, LoggerConfig loggerConf, Properties prop) {
		int count = 0;
		String element = prop.getProperty(initialKey + diferentialKey + count);
		while (element != null) {
			loggerConf.setFormatType(diferentialKey);
			loggerConf.setFormat(element);
			count++;
			element = prop.getProperty(initialKey + diferentialKey + count);
		}
	}
	
	private static void auxFilters(String initialKey, String diferentialKey, LoggerConfig loggerConf, Properties prop) {
		int count = 0;
		String element = prop.getProperty(initialKey + diferentialKey + count);
		while (element != null) {
			loggerConf.addFilterType(diferentialKey);
			loggerConf.addFilter(element);
			count++;
			element = prop.getProperty(initialKey + diferentialKey + count);
		}
	}
	
}
