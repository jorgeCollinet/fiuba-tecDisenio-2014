package ar.fiuba.tecnicas.filter;

import java.util.regex.Pattern;

/**
 * Clase encargada de filtrar mensaje segun la expresion regular establecida
 * 
 * @author Grupo3
 * 
 */
public class FilterRegex implements IFilter {
	protected String regex;

	public FilterRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean hasToLog(FilterData filterData) {
		return (Pattern.matches(this.regex, filterData.getMessage()));
	}
}
