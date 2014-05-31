package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el caracter de escape
 */
public class SubformatoEscape extends Subformato
{

	public SubformatoEscape(String match)
	{
		super(match);
	}
	
	@Override
	public String darFormato(ParametrosSubformato parametros) 
	{
		return "%";
	}

	@Override
	public String getJSONTag() 
	{
		return null;
	}

}
