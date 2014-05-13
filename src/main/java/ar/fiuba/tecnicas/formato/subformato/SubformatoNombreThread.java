package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.ISubformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el nombre del thread donde se llamo a darFormato
 */
public class SubformatoNombreThread implements ISubformato
{
	@Override
	public String darFormato(ParametrosSubformato parametros)
	{
		return parametros.getNombreThread();
	}
}
