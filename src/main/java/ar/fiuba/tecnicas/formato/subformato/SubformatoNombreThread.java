package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el nombre del thread donde se llamo a darFormato
 */
public class SubformatoNombreThread extends Subformato
{
	
	public SubformatoNombreThread(String match)
	{
		super(match);
	}
	
	@Override
	public String darFormato(ParametrosSubformato parametros)
	{
		return parametros.getNombreThread();
	}
}
