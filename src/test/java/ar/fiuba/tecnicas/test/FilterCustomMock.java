package ar.fiuba.tecnicas.test;

import ar.fiuba.tecnicas.filter.FilterData;
import ar.fiuba.tecnicas.filter.IFilter;

/**
 * Clase de ejemplo (el usuario deberia crear su propia clase con la logica para ver si debe logear)
 * @author Grupo3
 * 
 */
public class FilterCustomMock implements IFilter{

	public FilterCustomMock() {
		
	}

	
	public boolean hasToLog(FilterData filterData) {
		return true;
	}
}
