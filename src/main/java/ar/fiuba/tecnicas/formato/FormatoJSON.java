package ar.fiuba.tecnicas.formato;

import ar.fiuba.tecnicas.logging.Niveles;

/**
 * Devuelve un formato
 * @author Administrador
 *
 */
public class FormatoJSON extends Formato {

	public FormatoJSON(String patron, String separador) 
	{
		super(patron, separador);
	}

	public FormatoJSON(String patron) 
	{
		super(patron);
	}

	@Override
	protected String _darFormato(String mensaje, Niveles nivel, String logger)
	{
		ParametrosSubformato parametros = new ParametrosSubformato
				(mensaje, nivel, separador, Thread.currentThread(), logger);
		String resultado = "{";
		for (Subformato subformato : subformatos)
		{
			if (subformato.getJSONTag() == null) continue;
			if (resultado.length() > 1) resultado += ", ";
			resultado += "'"+subformato.getJSONTag()+"': ";
			resultado += "'"+subformato.darFormato(parametros)+"'";
		}
		return resultado+"}";
	}

}
