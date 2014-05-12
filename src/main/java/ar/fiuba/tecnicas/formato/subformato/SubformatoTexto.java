package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.ISubformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

public class SubformatoTexto implements ISubformato 
{
	private String texto;
	
	public SubformatoTexto(String texto) 
	{
		this.texto = texto;
	}

	@Override
	public String darFormato(ParametrosSubformato parametros)
	{
		return texto;
	}

}
