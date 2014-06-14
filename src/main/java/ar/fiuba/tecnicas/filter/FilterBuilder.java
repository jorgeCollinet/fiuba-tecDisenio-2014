package ar.fiuba.tecnicas.filter;

import java.util.ArrayList;

import ar.fiuba.tecnicas.logging.Level;

/**
 * Clase encargada de construir los distintos Filters que implementan la
 * interfaz IFilter
 * 
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
	public static ArrayList<IFilter> generateFilters(String loggerName, Level level, ArrayList<String> filterList) {
		ArrayList<IFilter> filters = new ArrayList<IFilter>();

		FilterNivel filterNivel = new FilterNivel(level);
		FilterNombre filterNombre = new FilterNombre(loggerName);
		filters.add(filterNombre);
		filters.add(filterNivel);

		for (String item : filterList) {
			if (item.contains(FilterType.BehaveRegex.toString())) {
				String regulaExpresion = item.split(">")[1];
				FilterRegex filterRegex = new FilterRegex(regulaExpresion);
				filters.add(filterRegex);
			} else if (item.contains(FilterType.BehaveClass.toString())) {
				String nombreClaseCustom = item.split(">")[1];
				try {
					FilterCustom filterCustom = FilterCustom.generateFilterCustom(nombreClaseCustom);
					filters.add(filterCustom);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return filters;
	}
}
