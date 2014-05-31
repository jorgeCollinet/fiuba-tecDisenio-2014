package ar.fiuba.tecnicas.filter;

/**
 * Clase encargada de filtrar por nivel
 * @author Grupo3
 * 
 */
public class FilterNombre implements IFilter{
	protected String nombre;

	public FilterNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean hasToLog(FilterData filterData) {
		return (this.nombre == filterData.getNombre());
	}
}
