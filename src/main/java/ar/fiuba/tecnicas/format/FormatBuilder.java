package ar.fiuba.tecnicas.format;
/**
 * 
 * @author grupo3
 * Clase encargada de la generacion de los distintos formatos
 */
public class FormatBuilder {
	/**
	 * 
	 * @param dataFormats String por el cual se generarán los formatos
	 * @param defaultPattern si no se encuentra un formato dentro de dataformats se utilizará este formato
	 * @param separator separador del formato
	 * @return
	 */
	public static Format generateFormats(String dataFormats, String defaultPattern, String separator) {
		String[] list = dataFormats.split(",");
		for (String item : list) {
			if (item.contains("FormatoJson")) {
				String patron = item.split(">")[1];
				return new JSONFormat(patron, separator);
			}
			if (item.contains("Formato")) {
				String patron = item.split(">")[1];
				return new Format(patron, separator);

			}
		}
		return new Format(defaultPattern,separator);
	}

}
