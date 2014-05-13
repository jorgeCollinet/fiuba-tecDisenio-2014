package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.ISubformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve un texto estático cualquiera
 */
public class SubformatoTexto implements ISubformato 
{
	private String texto;
	
	/**
	 * @param texto		Texto estático que devuelve este subformato
	 */
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
