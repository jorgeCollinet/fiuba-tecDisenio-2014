package ar.fiuba.tecnicas.formato;

/**
 * Interface estilo command para los subformatos
 */
public interface ISubformato 
{
	/**
	 * Método que al ser llamado devuelve un string segun un formato
	 * @param parametros	Parametros necesarios
	 * @return				Un string con la información deseada
	 */
	public String darFormato(ParametrosSubformato parametros);
}
