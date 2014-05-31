package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.ParametrosSubformato;
import ar.fiuba.tecnicas.formato.Subformato;

public class SubformatoNombreLogger extends Subformato {

	public SubformatoNombreLogger(String match) 
	{
		super(match);
	}

	@Override
	public String darFormato(ParametrosSubformato parametros)
	{
		return parametros.getLogger();
	}
	
	@Override
	public String getJSONTag() 
	{
		return "logger";
	}

}
