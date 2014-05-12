package ar.fiuba.tecnicas.formato;

import java.lang.Thread;
import java.util.LinkedList;
import java.util.regex.*;

import ar.fiuba.tecnicas.formato.subformato.*;
import ar.fiuba.tecnicas.logging.Niveles;

/**
 * Clase encargada de dar formato a un texto tras asignarsele un patrón
 */
public class Formato
{
	private String separador = "-";
	private LinkedList<ISubformato> subformatos;
	private static Pattern posiblesPatrones = Pattern.compile("(%[ptmnLFM%])|(%d\\{[^\\}]*\\})");
	
	/**
	 * Crea un nuevo formato con el patrón asignador
	 * Ver setFormato para la sintaxis soportada
	 * @param patron	String con el patrón
	 */
	public Formato(String patron, String separador) 
	{
		setFormato(patron, separador);
	}
	
	/**
	 * Dado un string con el formato deseado, crea los objetos internos del formato
	 * @param patron	String con los patrones
	 */
	public void setFormato(String patron, String separador) 
	{
		if (separador != null)
			this.separador = separador;
		subformatos = new LinkedList<ISubformato>();
		// Parseo
		Matcher matcher = posiblesPatrones.matcher(patron);
		while (matcher.find())
		{
			int inicioMatch = matcher.start();
			if (inicioMatch != 0)
			{
				subformatos.add(new SubformatoTexto(patron.substring(0,inicioMatch)));
				patron = patron.substring(inicioMatch);
			}
			else
			{
				switch(patron.charAt(inicioMatch+1))
				{
					case 'd':
						subformatos.add(new SubformatoFecha(patron.substring(matcher.start()+3, matcher.end()-1)));
						break;
					case 'p':
						subformatos.add(new SubformatoNivel());
						break;
					case 't':
						subformatos.add(new SubformatoNombreThread());
						break;
					case 'm':
						subformatos.add(new SubformatoMensaje());
						break;
					case '%':
						subformatos.add(new SubformatoEscape());
						break;
					case 'n':
						subformatos.add(new SubformatoSeparador());
						break;
					case 'L':
						subformatos.add(new SubformatoNumeroLinea());
						break;
					case 'F':
						subformatos.add(new SubformatoNombreArchivo());
						break;
					case 'M':
						subformatos.add(new SubformatoNombreMetodo());
						break;
				}
				patron = patron.substring(matcher.end());
			}
			matcher.reset(patron);
		}
		// Quedo algun texto suelto
		if (patron.length() > 0) subformatos.add(new SubformatoTexto(patron));
	}
	
	/**
	 * Dado un mensaje y nivel, le aplica el formato previamente seteado
	 * @param mensaje	Mensaje al cual se le desea aplicar el formato predefinido
	 * @param nivel		Nivel del mensaje
	 * @return			El mensaje tras ser formateado
	 */
	public String darFormato(String mensaje, Niveles nivel)
	{
		ParametrosSubformato parametros = new ParametrosSubformato
					(mensaje, nivel, separador, Thread.currentThread());
		String resultado = new String();
		for (ISubformato subformato : subformatos)
		{
			resultado += subformato.darFormato(parametros);
		}
		return resultado;
	}

}