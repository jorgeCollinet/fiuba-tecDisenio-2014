package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el mensaje a logear
 */
public class SubformatoMensaje extends Subformato
{

	public SubformatoMensaje(String match)
	{
		super(match);
	}
	
	@Override
	public String darFormato(ParametrosSubformato parametros)
	{
		return parametros.getMensaje();
	}
}
