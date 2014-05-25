package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el nivel del mensaje a logear
 */
public class SubformatoNivel extends Subformato
{

	public SubformatoNivel(String match)
	{
		super(match);
	}
	
	@Override
	public String darFormato(ParametrosSubformato parametros) 
	{
		return parametros.getNivel();
	}
}
