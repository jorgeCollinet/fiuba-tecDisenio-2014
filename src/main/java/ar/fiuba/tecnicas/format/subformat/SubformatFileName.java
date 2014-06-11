package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve el nombre de archivo del método que llamo a darFormato
 * tras descartar los paquetes triviales en el stack de llamadas
 */
public class SubformatFileName extends Subformat 
{

	public SubformatFileName(String match)
	{
		super(match);
	}
	
	@Override
	public String giveFormat(SubformatParameters parameters) 
	{
		return parameters.getFileName();
	}
	
	@Override
	public String getJSONTag() 
	{
		return "filename";
	}
}
