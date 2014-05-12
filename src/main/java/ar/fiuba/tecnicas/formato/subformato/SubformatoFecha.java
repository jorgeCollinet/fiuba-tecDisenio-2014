package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.ISubformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubformatoFecha implements ISubformato
{
	private SimpleDateFormat formato;

	public SubformatoFecha(String formato) 
	{
		this.formato = new SimpleDateFormat(formato);
	}

	@Override
	public String darFormato(ParametrosSubformato parametros) 
	{
		return formato.format(new Date());
	}

}
