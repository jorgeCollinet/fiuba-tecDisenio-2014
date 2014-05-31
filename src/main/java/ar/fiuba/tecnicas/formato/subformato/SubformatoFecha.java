package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Subformato que devuelve la fecha con un formato especificado
 */
public class SubformatoFecha extends Subformato
{
	private SimpleDateFormat formato;
	
	public SubformatoFecha(String match)
	{
		super(match);
		String formato = match.substring(match.indexOf("{")+1,match.lastIndexOf("}"));
		this.formato = new SimpleDateFormat(formato);	
	}

	@Override
	public String darFormato(ParametrosSubformato parametros) 
	{
		return formato.format(new Date());
	}
	
	@Override
	public String getJSONTag() 
	{
		return "datetime";
	}

}
