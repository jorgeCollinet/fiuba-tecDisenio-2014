package ar.fiuba.tecnicas.format;

import java.util.ArrayList;

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
	public static Format generateFormats(ArrayList<String> formatList, String defaultPattern, String separator) {
		for (String item : formatList) {
			if (item.contains(FormatType.FormatoJson.toString())) {
				String patron = item.split(">")[1];
				return new JSONFormat(patron, separator);
			}
			if (item.contains(FormatType.Formato.toString())) {
				String patron = item.split(">")[1];
				return new Format(patron, separator);

			}
		}
		return new Format(defaultPattern,separator);
	}

}