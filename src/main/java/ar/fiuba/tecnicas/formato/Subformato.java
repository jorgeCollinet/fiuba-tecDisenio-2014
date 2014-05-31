package ar.fiuba.tecnicas.formato;

/**
 * Interface estilo command para los subformatos
 */
public abstract class Subformato 
{
	/**
	 * Constructor, pide el string de lo que se matcheo por si falta info
	 * @param match		El match
	 */
	public Subformato(String match)
	{

	}
	
	/**
	 * Método que al ser llamado devuelve un string segun un formato
	 * @param parametros	Parametros necesarios
	 * @return				Un string con la información deseada
	 */
	public abstract String darFormato(ParametrosSubformato parametros);
	
	/**
	 * Devuelve el un "tag" para poner al usar como formato JSON
	 * Devuelve null en caso de que no tenga sentido un tag para el subformato
	 * @return				Un string con la información deseada
	 */
	public abstract String getJSONTag();
}
