package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

public class SubformatLoggerName extends Subformat {

	public SubformatLoggerName(String match) 
	{
		super(match);
	}

	@Override
	public String giveFormat(SubformatParameters parameters)
	{
		return parameters.getLogger();
	}
	
	@Override
	public String getJSONTag() 
	{
		return "logger";
	}

}
