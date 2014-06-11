package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve un texto estático cualquiera
 */
public class SubformatText extends Subformat 
{
	private String texto;
	
	/**
	 * @param match		Texto estático que devuelve este subformato
	 */
	public SubformatText(String match) 
	{
		super(match);
		this.texto = match;
	}

	@Override
	public String giveFormat(SubformatParameters parameters)
	{
		return texto;
	}
	
	@Override
	public String getJSONTag() 
	{
		return null;
	}

}
