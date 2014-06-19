package ar.fiuba.tecnicas.format;

/**
 * 
 * @author grupo3 Clase encargada de la generacion de los distintos formatos
 */
public class FormatBuilder {
	/**
	 * 
	 * @param type		Tipo de formato
	 * @param pattern 	Patron del formato
	 * @param defaultPattern
	 *            si no se encuentra un formato dentro de dataformats se
	 *            utilizará este patron
	 * @param separator	Separador del formato
	 * @return
	 */
	public static Format generateFormat(FormatType type, String pattern,
			String defaultPattern, String separator) {
		if (type == FormatType.JSONFormat) {
			return new JSONFormat(pattern, separator);
		} else if (type == FormatType.Format) {
			return new Format(pattern, separator);

		} else
			return new Format(defaultPattern, separator);
	}

}
