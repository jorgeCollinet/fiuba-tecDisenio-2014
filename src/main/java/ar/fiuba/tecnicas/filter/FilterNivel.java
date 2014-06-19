package ar.fiuba.tecnicas.filter;

import ar.fiuba.tecnicas.logging.Level;

/**
 * Clase encargada de filtrar por nivel
 * 
 * @author Grupo3
 * 
 */
public class FilterNivel implements IFilter {
	protected Level nivel;

	public FilterNivel(Level nivel) {
		this.nivel = nivel;
	}

	@Override
	public boolean hasToLog(FilterData filterData) {
		return (this.nivel.compareTo(filterData.getNivel()) <= 0);
	}
}
