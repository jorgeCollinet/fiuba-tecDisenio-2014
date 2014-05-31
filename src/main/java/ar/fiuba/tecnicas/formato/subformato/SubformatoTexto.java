package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve un texto estático cualquiera
 */
public class SubformatoTexto extends Subformato 
{
	private String texto;
	
	/**
	 * @param match		Texto estático que devuelve este subformato
	 */
	public SubformatoTexto(String match) 
	{
		super(match);
		this.texto = match;
	}

	@Override
	public String darFormato(ParametrosSubformato parametros)
	{
		return texto;
	}
	
	@Override
	public String getJSONTag() 
	{
		return null;
	}

}
