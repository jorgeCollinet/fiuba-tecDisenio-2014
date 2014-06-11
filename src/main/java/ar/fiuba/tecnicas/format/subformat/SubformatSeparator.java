package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve el separador
 */
public class SubformatSeparator extends Subformat 
{
	
	public SubformatSeparator(String match)
	{
		super(match);
	}
	
	@Override
	public String giveFormat(SubformatParameters parameters) 
	{
		return parameters.getSeparator();
	}
	
	@Override
	public String getJSONTag() 
	{
		return null;
	}

}
