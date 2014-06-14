package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

public class LoggerConfig {

	private String name;
	private Level level;
	private String defaultFormat;
	private String separator;
	private ArrayList<String> outputs;
	private ArrayList<String> formats;
	private ArrayList<String> filters;
	
	public LoggerConfig() {
		name = "";
		outputs = new ArrayList<>();
		formats = new ArrayList<>();
		filters = new ArrayList<>();

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	
	public void setDefaultFormat(String defaultFormat) {
		this.defaultFormat = defaultFormat;
	}

	public void addOutput(String output) {
		this.outputs.add(output);
	}

	public void addFormat(String format) {
		this.formats.add(format);
	}

	public void addFilter(String filter) {
		this.filters.add(filter);
	}

	public String getName() {
		return name;
	}

	public Level getLevel() {
		return level;
	}

	public ArrayList<String> getOutputs() {
		return outputs;
	}

	public ArrayList<String> getFormats() {
		return formats;
	}

	public ArrayList<String> getFilters() {
		return filters;
	}
	
	public String getSeparator() {
		return separator;
	}
	
	public String getDefaultFormat() {
		return defaultFormat;
	}

}
