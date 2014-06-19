package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.List;

import ar.fiuba.tecnicas.filter.FilterType;
import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.output.OutputType;

public class LoggerConfig {

	private String name;
	private Level level;
	private FormatType formatType;
	private String format;
	private String defaultFormat;
	private String separator;
	private ArrayList<String> outputs;
	private ArrayList<OutputType> outputTypes;
	private ArrayList<String> filters;
	private ArrayList<FilterType> filterTypes;

	public LoggerConfig() {
		name = "";
		format = Format.defaultPattern;
		separator = Format.defaultSeparator;
		formatType = FormatType.Format;
		outputs = new ArrayList<>();
		filters = new ArrayList<>();
		outputTypes = new ArrayList<>();
		filterTypes = new ArrayList<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setLevel(String level) {
		this.level = Level.valueOf(level);
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public void setDefaultFormat(String defaultFormat) {
		this.defaultFormat = defaultFormat;
	}

	public void addOutput(String output, OutputType type) {
		this.outputs.add(output);
		this.outputTypes.add(type);
	}

	public void addOutput(String output, String type) {
		this.outputs.add(output);
		this.outputTypes.add(OutputType.valueOf(type));
	}

	public void setFormat(String format, FormatType type) {
		this.format = format;
		this.formatType = type;
	}

	public void setFormat(String format, String type) {
		this.format = format;
		this.formatType = FormatType.valueOf(type);
	}

	public void addFilter(String filter, FilterType type) {
		this.filters.add(filter);
		this.filterTypes.add(type);
	}

	public void addFilter(String filter, String type) {
		this.filters.add(filter);
		this.filterTypes.add(FilterType.valueOf(type));
	}

	public String getName() {
		return name;
	}

	public Level getLevel() {
		return level;
	}

	public List<String> getOutputs() {
		return outputs;
	}

	public String getFormat() {
		return format;
	}

	public List<String> getFilters() {
		return filters;
	}

	public String getSeparator() {
		return separator;
	}

	public String getDefaultFormat() {
		return defaultFormat;
	}

	public FormatType getFormatType() {
		return this.formatType;
	}

	public List<OutputType> getOutputTypes() {
		return this.outputTypes;
	}

	public List<FilterType> getFilterTypes() {
		return this.filterTypes;
	}

}
