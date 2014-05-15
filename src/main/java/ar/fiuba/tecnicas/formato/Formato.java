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
	private static Pattern posiblesPatrones = Pattern.compile("(%[ptmnLFM%])|(%d\\{[^\\}]*\\})");
	public static final String petronDefault = "%m";
	public static final String separadorDefault = "-";
	
	private String separador = separadorDefault;
	private LinkedList<ISubformato> subformatos;
	
	
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
		subformatos = new LinkedList<ISubformato>();
		// Parseo
		Matcher matcher = posiblesPatrones.matcher(patron);
		while (matcher.find())
		{
			int inicioMatch = matcher.start();
			int finMatch = matcher.end();
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
						subformatos.add(new SubformatoFecha(patron.substring(matcher.start()+3, finMatch-1)));
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
				patron = patron.substring(finMatch);
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