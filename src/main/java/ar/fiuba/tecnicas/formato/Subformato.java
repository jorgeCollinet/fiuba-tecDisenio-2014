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
}
