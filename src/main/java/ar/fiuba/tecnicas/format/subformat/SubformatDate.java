package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Subformato que devuelve la fecha con un formato especificado
 */
public class SubformatDate extends Subformat
{
	private SimpleDateFormat format;
	
	public SubformatDate(String match)
	{
		super(match);
		String formato = match.substring(match.indexOf("{")+1,match.lastIndexOf("}"));
		this.format = new SimpleDateFormat(formato);	
	}

	@Override
	public String giveFormat(SubformatParameters parameters) 
	{
		return format.format(new Date());
	}
	
	@Override
	public String getJSONTag() 
	{
		return "datetime";
	}

}
