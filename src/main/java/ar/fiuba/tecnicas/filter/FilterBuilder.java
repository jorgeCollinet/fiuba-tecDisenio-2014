package ar.fiuba.tecnicas.filter;

import java.util.ArrayList;

import ar.fiuba.tecnicas.logging.Niveles;

/**
 * Clase encargada de construir los distintos Filters que implementan la interfaz IFilter
 * @author Grupo3
 *
 */
public class FilterBuilder {

	protected FilterBuilder() {
	}

	/**
	 * A partir de un string con un determinado formato genera los distintos
	 * tipos de outputs, dependiendo de cada output puede tener un valor
	 * asociado, ej: nombre de archivo
	 * 
	 * @param outputStringValues
	 * @return IOutput
	 * @throws Exception
	 */
	public static ArrayList<IFilter> generateFilters(Niveles nivel, String filterStringValues){
		ArrayList<IFilter> filters = new ArrayList<IFilter>();
		String[] list = filterStringValues.split(",");
		String nombreLogger = list[0];
		
		FilterNivel filterNivel = new FilterNivel(nivel);
		FilterNombre filterNombre = new FilterNombre(nombreLogger);
		
		/*
		ACA FALTA EL PARSEO DE filterStringValues
		me deberian llegar el nombreLogger,nivel,regex,nombreClaseCustom y genero los filtros 
		FilterRegex filterRegex = new FilterRegex(regex);
		FilterCustom filterCustom = new FilterCustom(nombreClaseCustom);
		filters.add(filterRegex);
		filters.add(filterCustom);*/
		
		filters.add(filterNombre);
		filters.add(filterNivel);
		
		return filters;
		
	}

}
