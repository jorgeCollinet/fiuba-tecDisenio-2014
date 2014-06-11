package ar.fiuba.tecnicas.format;

import java.lang.Thread;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;

import ar.fiuba.tecnicas.format.subformat.*;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.Level;

/**
 * Clase base para las clases encagadas de dar formato a un texto tras asignarsele un patrón
 */
public class Format
{
	public static final String patronDefault = "%m";
	public static final String separadorDefault = "-";
	
	protected String separator = separadorDefault;
	protected LinkedList<Subformat> subformats;
	
	// Tuplas de Patron-Subformato para reemplazar
	private static List<MatchSubformatTuple> matchTuples 
		= MatchSubformatListBuilder.loadFromFile();	
		
	/**
	 * Crea un nuevo formato con el patrón y separador asignados
	 * Ver setFormato(String, String) para la sintaxis soportada
	 * @param patron	String con el patrón
	 * @param separador Separador a utilizar
	 */
	public Format(String patron, String separador) 
	{
		setFormat(patron, separador);
	}
	
	/**
	 * Crea un nuevo formato con el patrón asignado y separador default
	 * Ver setFormato(String, String) para la sintaxis soportada
	 * @param patron	String con el patrón
	 */
	public Format(String patron) 
	{
		setFormat(patron, null);
	}
	
	/**
	 * Dado un string con el formato deseado, crea los objetos internos del formato
	 * Utiliza el separador por defecto
	 * Ver setFormato(String, String) para la sintaxis soportada
	 * @param pattern	String con los patrones
	 */
	public void setFormat(String pattern) 
	{
		setFormat(pattern, null);
	}
	
	/**
	 * Dado un string con el formato deseado, crea los objetos internos del formato
	 * @param pattern	String con los patrones
	 * @param separator	Separador a utilizar
	 * Sintaxis soportada en patron:
	 * 	%d{xxxxxxx} acepta cualquier formato valido de SimpleDateFormat.
	 *	%p muestra el Nivel del mensaje.
	 *	%t muestra el nombre del thread actual.
	 *	%m muestra el contenido del mensaje logueado por el usuario.
	 *	%% muestra un % (es el escape de %).
	 * 	%n muestra el separador
	 *  %g muestra el nombre del logger
	 *	%L muestra el numero de linea desde donde se llamo a darFormato, ignorando paquetes internos
	 *	%F muestra el nombre de archivo desde donde se llamo a darFormato, ignorando paquetes internos
	 *	%M muestra el nombre del metodo desde donde se llamo a darFormato, ignorando paquetes internos
	 */
	public void setFormat(String pattern, String separator) 
	{
		if (separator != null)
			this.separator = separator;
		if (pattern == null)
			pattern = patronDefault;
		subformats = new LinkedList<Subformat>();
		// Parseo
		while (pattern.length() > 0)
		{
			int matchStart = pattern.length();
			int matchEnd = pattern.length();
			MatchSubformatTuple match = null;
			for (MatchSubformatTuple tupla : matchTuples)
			{
				Matcher matcher = tupla.getMatcher(pattern);
				if (matcher.find())
				{
					if (matcher.start() < matchStart)
					{
						matchStart = matcher.start();
						matchEnd = matcher.end();
						match = tupla;
					}
				}
			}
			if (matchStart != 0) // El patron no estaba al inicio o no quedan patrones
			{
				subformats.add(new SubformatText(pattern.substring(0,matchStart)));
				pattern = pattern.substring(matchStart);
			}
			else if (match != null) // Patron al inicio
			{
				subformats.add(match.getNewSubformat(pattern.substring(matchStart, matchEnd)));
				pattern = pattern.substring(matchEnd);
			}
		}
	}
	
	/**
	 * Retorna Logger.DEFAULT_NAME_LOGGER para %g. Usar darFormato(String, Niveles, String)
	 * Dado un mensaje y nivel, le aplica el formato previamente seteado
	 * @param message	Mensaje al cual se le desea aplicar el formato predefinido
	 * @param level		Nivel del mensaje
	 * @return			El mensaje tras ser formateado
	 */
	public String giveFormat(String message, Level level)
	{
		return giveFormat(message, level, Logger.DEFAULT_NAME_LOGGER);
	}
	
	/**
	 * Dado un mensaje, nivel y nombre de logger, le aplica el formato previamente seteado
	 * @param message	Mensaje al cual se le desea aplicar el formato predefinido
	 * @param level		Nivel del mensaje
	 * @param logger	Nombre del logger
	 * @return			El mensaje tras ser formateado
	 */
	public String giveFormat(String message, Level level, String logger)
	{
		SubformatParameters parametros = new SubformatParameters
				(message, level, separator, Thread.currentThread(), logger);
		String resultado = new String();
		for (Subformat subformato : subformats)
		{
			resultado += subformato.giveFormat(parametros);
		}
		return resultado;
	}

}