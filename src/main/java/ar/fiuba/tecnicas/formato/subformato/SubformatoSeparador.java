package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el separador
 */
public class SubformatoSeparador extends Subformato 
{
	
	public SubformatoSeparador(String match)
	{
		super(match);
	}
	
	@Override
	public String darFormato(ParametrosSubformato parametros) 
	{
		return parametros.getSeparador();
	}
	
	@Override
	public String getJSONTag() 
	{
		return null;
	}

}
