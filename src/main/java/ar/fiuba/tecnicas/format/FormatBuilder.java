package ar.fiuba.tecnicas.format;

/**
 * 
 * @author grupo3 Clase encargada de la generacion de los distintos formatos
 */
public class FormatBuilder {
	/**
	 * 
	 * @param dataFormats
	 *            String por el cual se generarán los formatos
	 * @param defaultPattern
	 *            si no se encuentra un formato dentro de dataformats se
	 *            utilizará este formato
	 * @param separator
	 *            separador del formato
	 * @return
	 */
	public static Format generateFormat(FormatType tipo, String patron,
			String defaultPattern, String separator) {
		if (tipo == FormatType.JSONFormat) {
			return new JSONFormat(patron, separator);
		} else if (tipo == FormatType.Format) {
			return new Format(patron, separator);

		} else
			return new Format(defaultPattern, separator);
	}

}
