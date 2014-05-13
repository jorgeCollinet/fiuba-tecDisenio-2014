package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.ISubformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Subformato que devuelve la fecha con un formato especificado
 */
public class SubformatoFecha implements ISubformato
{
	private SimpleDateFormat formato;

	/**
	 * @param formato	Segun la especificacion de SimpleDateFormat
	 */
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
