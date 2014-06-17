package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.List;

import ar.fiuba.tecnicas.filter.FilterType;
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
		format = "";
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

	public void addOutput(String output) {
		this.outputs.add(output);
	}

	public void setFormat(String format) {
		this.format = format;
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
	
	public void setFormatType(String formatType)
	{
		this.formatType = FormatType.valueOf(formatType);
	}
	
	public void setFormatType(FormatType formatType)
	{
		this.formatType = formatType;
	}
	
	public FormatType getFormatType()
	{
		return this.formatType;
	}
	
	public void addOutputType(String outputType)
	{
		this.outputTypes.add(OutputType.valueOf(outputType));
	}
	
	public void addOutputType(OutputType outputType)
	{
		this.outputTypes.add(outputType);
	}
	
	public List<OutputType> getOutputTypes()
	{
		return this.outputTypes;
	}
	
	public void addFilterType(String filterType)
	{
		this.filterTypes.add(FilterType.valueOf(filterType));
	}
	
	public void addFilterType(FilterType filterType)
	{
		this.filterTypes.add(filterType);
	}
	
	public List<FilterType> getFilterTypes()
	{
		return this.filterTypes;
	}

}
