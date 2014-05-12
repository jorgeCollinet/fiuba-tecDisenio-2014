package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.ISubformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

public class SubformatoEscape implements ISubformato
{
	@Override
	public String darFormato(ParametrosSubformato parametros) 
	{
		return "%";
	}

}
