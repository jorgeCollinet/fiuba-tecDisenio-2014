package ar.fiuba.tecnicas.format;

/**
 * Interface estilo command para los subformatos
 */
public abstract class Subformat {
	/**
	 * Constructor, pide el string de lo que se matcheo por si falta info
	 * 
	 * @param match
	 *            El match
	 */
	public Subformat(String match) {

	}

	/**
	 * Método que al ser llamado devuelve un string segun un formato
	 * 
	 * @param parameters
	 *            Parametros necesarios
	 * @return Un string con la información deseada
	 */
	public abstract String giveFormat(SubformatParameters parameters);

	/**
	 * Devuelve el un "tag" para poner al usar como formato JSON Devuelve null
	 * en caso de que no tenga sentido un tag para el subformato
	 * 
	 * @return Un string con la información deseada
	 */
	public abstract String getJSONTag();
}
