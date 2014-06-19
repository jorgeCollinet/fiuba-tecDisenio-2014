package ar.fiuba.tecnicas.filter;

import java.util.ArrayList;
import java.util.List;

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
	public static List<IFilter> generateFilters(String loggerName, Level level,
			List<String> filterList, List<FilterType> typeList)
			throws Exception {
		if (typeList.size() < filterList.size()) {
			throw new Exception(
					"Desacuerdo entre numero de filtros y sus tipos.");
		}

		ArrayList<IFilter> filters = new ArrayList<>();

		FilterNivel filterNivel = new FilterNivel(level);
		FilterNombre filterNombre = new FilterNombre(loggerName);
		filters.add(filterNombre);
		filters.add(filterNivel);

		for (int i = 0; i < filterList.size(); ++i) {
			if (typeList.get(i) == FilterType.BehaveRegex) {
				FilterRegex filterRegex = new FilterRegex(filterList.get(i));
				filters.add(filterRegex);
			} else if (typeList.get(i) == FilterType.BehaveClass) {
				String nombreClaseCustom = filterList.get(i);
				FilterCustom filterCustom = FilterCustom.generateFilterCustom(nombreClaseCustom);
				filters.add(filterCustom);
			}
		}
		return filters;
	}
}
