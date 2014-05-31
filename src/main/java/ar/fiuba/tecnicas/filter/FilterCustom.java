package ar.fiuba.tecnicas.filter;

import ar.fiuba.tecnicas.logging.Niveles;

/**
 * Clase encargada de filtrar por nivel
 * @author Grupo3
 * 
 */
public class FilterCustom implements IFilter{

	public FilterCustom() {
		
	}
	
	@Override
	public boolean hasToLog(FilterData filterData) {
		return true;
	}
}
