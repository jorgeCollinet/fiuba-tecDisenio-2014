package ar.fiuba.tecnicas.filter;

/**
 * Clase de ejemplo (el usuario deberia crear su propia clase con la logica para ver si debe logear)
 * @author Grupo3
 * 
 */
public class FilterCustomHorario implements IFilter{

	public FilterCustomHorario() {
		
	}

	
	public boolean hasToLog(FilterData filterData) {
		return true;
	}
}
