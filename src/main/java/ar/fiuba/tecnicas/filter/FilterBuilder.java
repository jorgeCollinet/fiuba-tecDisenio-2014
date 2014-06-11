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
	public static ArrayList<IFilter> generateFilters(Level nivel, String filterStringValues) {
		ArrayList<IFilter> filters = new ArrayList<IFilter>();
		String[] list = filterStringValues.split(",");
		String nombreLogger = list[0];

		FilterNivel filterNivel = new FilterNivel(nivel);
		FilterNombre filterNombre = new FilterNombre(nombreLogger);
		filters.add(filterNombre);
		filters.add(filterNivel);

		for (String item : list) {
			if (item.contains("BehaveRegex")) {
				String regulaExpresion = item.split(">")[1];
				FilterRegex filterRegex = new FilterRegex(regulaExpresion);
				filters.add(filterRegex);
			} else if (item.contains("BehaveClass")) {
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
