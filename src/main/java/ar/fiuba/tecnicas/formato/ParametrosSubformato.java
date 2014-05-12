package ar.fiuba.tecnicas.formato;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.fiuba.tecnicas.logging.Niveles;

/**
 * Clase utilizada para pasar los parametros necesarios al subformato
 */
public class ParametrosSubformato 
{
	private String mensaje;
	private Niveles nivel;
	private String separador;
	private Thread thread;
	private int numeroLinea;
	private String nombreMetodo;
	private String nombreArchivo;
	private boolean procesoStack = false;
	
	/**
	 * Constructor
	 * @param mensaje	Mensaje
	 * @param nivel		Nivel del mensaje
	 * @param separador	Separador de campos
	 * @param thread	Thread
	 */
	public ParametrosSubformato(String mensaje, Niveles nivel, String separador, Thread thread)
	{
		this.mensaje = mensaje;
		this.nivel = nivel;
		this.separador = separador;
		this.thread = thread;
	}
	
	public String getMensaje()
	{
		if (mensaje == null) return "";
		return mensaje;
	}
	
	public String getNivel()
	{
		if (nivel == null) return "";
		return nivel.toString();
	}

	public String getSeparador()
	{
		return separador;
	}
			
	public String getNombreThread()
	{
		return thread.getName();
	}
	
	public String getNombreMetodo()
	{
		if (!procesoStack) obtenerDatosStack(thread);
		return nombreMetodo;
	}
	
	public String getNombreArchivo()
	{
		if (!procesoStack) obtenerDatosStack(thread);
		return nombreArchivo;
	}
	
	public String getNumeroLinea()
	{
		if (!procesoStack) obtenerDatosStack(thread);
		return ""+numeroLinea;
	}

	
	/**
	 * Dado un thread, obtiene de su stack información tras
	 * descartar las relacionadas al paquete de login
	 * @param thread	Thread a analizar
	 */
	private void obtenerDatosStack(Thread thread)
	{
		StackTraceElement[] stack = thread.getStackTrace();
		int i = 0;
		while (contienePaquetesTriviales(stack[i]))
		{
			++i;
		}
		numeroLinea = stack[i].getLineNumber();
		nombreArchivo = stack[i].getFileName();
		nombreMetodo = stack[i].getMethodName();
		procesoStack = true;
	}
	
	private static Pattern paquetesTriviales = Pattern.compile("(java.*)|" +
			"(ar.fiuba.tecnicas.formato.*)|(ar.fiuba.tecnicas.logging.*)|" +
			"(sun.*)|(org.junit.*)");
	private boolean contienePaquetesTriviales(StackTraceElement elemento)
	{
		String clase = elemento.getClassName();
		return paquetesTriviales.matcher(clase).matches();
	}
}
