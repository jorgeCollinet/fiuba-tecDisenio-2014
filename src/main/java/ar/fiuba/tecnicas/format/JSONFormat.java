package ar.fiuba.tecnicas.format;

import ar.fiuba.tecnicas.logging.Level;

/**
 * Devuelve un formato
 * @author Administrador
 *
 */
public class JSONFormat extends Format {

	public JSONFormat(String patron, String separador) 
	{
		super(patron, separador);
	}

	public JSONFormat(String patron) 
	{
		super(patron);
	}

	@Override
	public String giveFormat(String message, Level level, String logger)
	{
		SubformatParameters parameters = new SubformatParameters
				(message, level, separator, Thread.currentThread(), logger);
		String resultado = "{";
		for (Subformat subformato : subformats)
		{
			if (subformato.getJSONTag() == null) continue;
			if (resultado.length() > 1) resultado += ", ";
			resultado += "'"+subformato.getJSONTag()+"': ";
			resultado += "'"+subformato.giveFormat(parameters)+"'";
		}
		return resultado+"}";
	}

}
