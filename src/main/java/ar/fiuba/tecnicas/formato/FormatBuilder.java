package ar.fiuba.tecnicas.formato;
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
	public static Formato generateFormats(String dataFormats, String defaultPattern, String separator) {
		String[] list = dataFormats.split(",");
		for (String item : list) {
			if (item.contains("FormatoJson")) {
				String patron = item.split(">")[1];
				return new FormatoJSON(patron, separator);
			}
			if (item.contains("Formato")) {
				String patron = item.split(">")[1];
				return new Formato(patron, separator);

			}
		}
		return new Formato(defaultPattern,separator);
	}

}
