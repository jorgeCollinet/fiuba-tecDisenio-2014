package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve el caracter de escape
 */
public class SubformatEscape extends Subformat
{

	public SubformatEscape(String match)
	{
		super(match);
	}
	
	@Override
	public String giveFormat(SubformatParameters parameters) 
	{
		return "%";
	}

	@Override
	public String getJSONTag() 
	{
		return null;
	}

}
