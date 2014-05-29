package ar.fiuba.tecnicas.formato;

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
	private String numeroLinea;
	private String nombreMetodo;
	private String nombreArchivo;
	private boolean procesoStack = false;
	private static Pattern paquetesTriviales = Pattern.compile("(java\\..*)|" +
			"(ar\\.fiuba\\.tecnicas\\.formato.*)|(ar\\.fiuba\\.tecnicas\\.logging.*)|" +
			"(sun\\..*)|(org\\.junit\\..*)");
	
	/**
	 * Constructor
	 * @param mensaje	Mensaje				Mensaje a logear
	 * @param nivel		Nivel del mensaje	Nivel del mensaje
	 * @param separador	Separador 			Separador de campos
	 * @param thread	Thread				Thread desde el cual se llama
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
		return numeroLinea;
	}

	
	/**
	 * Dado un thread, obtiene la información de su stack tras descartar las
	 * relacionadas al paquete de login y a paquetes de java/junit/etc
	 * @param thread	Thread a analizar
	 */
	private void obtenerDatosStack(Thread thread)
	{
		StackTraceElement[] stack = thread.getStackTrace();
		int i = 0;
		while (i < stack.length && contienePaquetesTriviales(stack[i]))
		{
			++i;
		}
		if (i >= stack.length)
		{
			numeroLinea = nombreArchivo = nombreMetodo = "Error al parsear stacktrace";
		}
		else
		{
			numeroLinea = ""+stack[i].getLineNumber();
			nombreArchivo = stack[i].getFileName();
			nombreMetodo = stack[i].getMethodName();
		}
		procesoStack = true;
	}
	
	/**
	 * Avisa si un elemento del stack pertenece a paquetes de java/sun/junit/logging
	 * @param elemento	Elemento del stack
	 * @return			True si el paquete de ese elemento del stack pertenece a los nombrados
	 */
	private boolean contienePaquetesTriviales(StackTraceElement elemento)
	{
		String clase = elemento.getClassName();
		return paquetesTriviales.matcher(clase).matches();
	}
}
