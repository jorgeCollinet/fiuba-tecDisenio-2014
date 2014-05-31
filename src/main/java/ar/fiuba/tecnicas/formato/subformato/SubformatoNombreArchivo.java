package ar.fiuba.tecnicas.formato.subformato;

import ar.fiuba.tecnicas.formato.Subformato;
import ar.fiuba.tecnicas.formato.ParametrosSubformato;

/**
 * Subformato que devuelve el nombre de archivo del método que llamo a darFormato
 * tras descartar los paquetes triviales en el stack de llamadas
 */
public class SubformatoNombreArchivo extends Subformato 
{

	public SubformatoNombreArchivo(String match)
	{
		super(match);
	}
	
	@Override
	public String darFormato(ParametrosSubformato parametros) 
	{
		return parametros.getNombreArchivo();
	}
	
	@Override
	public String getJSONTag() 
	{
		return "filename";
	}
}
