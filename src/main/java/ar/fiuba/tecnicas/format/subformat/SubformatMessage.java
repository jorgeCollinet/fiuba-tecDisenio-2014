package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve el mensaje a logear
 */
public class SubformatMessage extends Subformat
{

	public SubformatMessage(String match)
	{
		super(match);
	}
	
	@Override
	public String giveFormat(SubformatParameters parameters)
	{
		return parameters.getMessage();
	}
	
	@Override
	public String getJSONTag() 
	{
		return "message";
	}
}
