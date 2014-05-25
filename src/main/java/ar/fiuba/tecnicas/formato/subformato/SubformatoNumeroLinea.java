package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el numero de linea donde se llamo a darFormato
 * tras descartar los paquetes triviales en el stack de llamadas
 */
public class SubformatoNumeroLinea extends Subformato 
{
	
	public SubformatoNumeroLinea(String match)
	{
		super(match);
	}
	
	@Override
	public String darFormato(ParametrosSubformato parametros)
	{
		return parametros.getNumeroLinea();
	}
}
