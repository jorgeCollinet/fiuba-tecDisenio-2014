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
	public static final String petronDefault = "%m";
	public static final String separadorDefault = "-";
	
	private static final LinkedList<TuplaMatchSubformato> subFormatos = getSubformatos();
	private String separador = separadorDefault;
	private LinkedList<Subformato> subformatos;
	
	/**
	 * Crea las tuplas de Patron-Subformato para reemplazar
	 * @return		Una lista con las tuplas
	 */
	private static LinkedList<TuplaMatchSubformato> getSubformatos()
	{	
		LinkedList<TuplaMatchSubformato> lista = new LinkedList<TuplaMatchSubformato>();
		lista.push(new TuplaMatchSubformato("%%",SubformatoEscape.class));
		lista.push(new TuplaMatchSubformato("%d\\{[^\\}]*\\}",SubformatoFecha.class));
		lista.push(new TuplaMatchSubformato("%m",SubformatoMensaje.class));
		lista.push(new TuplaMatchSubformato("%p",SubformatoNivel.class));
		lista.push(new TuplaMatchSubformato("%F",SubformatoNombreArchivo.class));
		lista.push(new TuplaMatchSubformato("%M",SubformatoNombreMetodo.class));
		lista.push(new TuplaMatchSubformato("%t",SubformatoNombreThread.class));
		lista.push(new TuplaMatchSubformato("%L",SubformatoNumeroLinea.class));
		lista.push(new TuplaMatchSubformato("%n",SubformatoSeparador.class));
		return lista;
	}
	
	/**
	 * Crea un nuevo formato con el patrón y separador asignados
	 * Ver setFormato(String, String) para la sintaxis soportada
	 * @param patron	String con el patrón
	 * @param separador Separador a utilizar
	 */
	public Formato(String patron, String separador) 
	{
		setFormato(patron, separador);
	}
	
	/**
	 * Crea un nuevo formato con el patrón asignado y separador default
	 * Ver setFormato(String, String) para la sintaxis soportada
	 * @param patron	String con el patrón
	 */
	public Formato(String patron) 
	{
		setFormato(patron, null);
	}
	
	/**
	 * Dado un string con el formato deseado, crea los objetos internos del formato
	 * Utiliza el separador por defecto
	 * Ver setFormato(String, String) para la sintaxis soportada
	 * @param patron	String con los patrones
	 */
	public void setFormato(String patron) 
	{
		setFormato(patron, null);
	}
	
	/**
	 * Dado un string con el formato deseado, crea los objetos internos del formato
	 * @param patron	String con los patrones
	 * @param separador	Separador a utilizar
	 * Sintaxis soportada en patron:
	 * 	%d{xxxxxxx} acepta cualquier formato valido de SimpleDateFormat.
	 *	%p muestra el Nivel del mensaje.
	 *	%t muestra el nombre del thread actual.
	 *	%m muestra el contenido del mensaje logueado por el usuario.
	 *	%% muestra un % (es el escape de %).
	 * 	%n muestra el separador
	 *	%L muestra el numero de linea desde donde se llamo a darFormato, ignorando paquetes internos
	 *	%F muestra el nombre de archivo desde donde se llamo a darFormato, ignorando paquetes internos
	 *	%M muestra el nombre del metodo desde donde se llamo a darFormato, ignorando paquetes internos
	 */
	public void setFormato(String patron, String separador) 
	{
		if (separador != null)
			this.separador = separador;
		if (patron == null)
			patron = petronDefault;
		subformatos = new LinkedList<Subformato>();
		// Parseo
		while (patron.length() > 0)
		{
			int inicioMatch = patron.length();
			int finMatch = patron.length();
			TuplaMatchSubformato match = null;
			for (TuplaMatchSubformato tupla : subFormatos)
			{
				Matcher matcher = tupla.getMatcher(patron);
				if (matcher.find())
				{
					if (matcher.start() < inicioMatch)
					{
						inicioMatch = matcher.start();
						finMatch = matcher.end();
						match = tupla;
					}
				}
			}
			if (inicioMatch != 0) // El patron no estaba al inicio o no quedan patrones
			{
				subformatos.add(new SubformatoTexto(patron.substring(0,inicioMatch)));
				patron = patron.substring(inicioMatch);
			}
			else if (match != null) // Patron al inicio
			{
				subformatos.add(match.getNewSubformato(patron.substring(inicioMatch, finMatch)));
				patron = patron.substring(finMatch);
			}
		}
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
		for (Subformato subformato : subformatos)
		{
			resultado += subformato.darFormato(parametros);
		}
		return resultado;
	}

}