package ar.fiuba.tecnicas.logging;

import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.output.IOutput;

/**
 * Clase encargada de verificar si el mensaje que recibe corresponde o no con su
 * nivel y enviarle el mensaje formateado mediante Formato a alguna clase que
 * implemente la interfaz IOutput
 * 
 * @author Grupo3
 * 
 */

public class Logger {
	protected IOutput out;
	protected Niveles nivel;
	protected Formato formato;
	protected String nombre;
	protected static final String DEFAULT_NAME_LOGGER = "";

	/**
	 * @param nivel
	 *            nivel de log
	 * @param salida
	 */
	public Logger(Niveles nivel, IOutput salida, Formato format) {
		this.nivel = nivel;
		this.formato = format;
		out = salida;
		this.nombre=DEFAULT_NAME_LOGGER;
	}
	
	public Logger(String nombre,Niveles nivel, IOutput salida, Formato format){
		this(nivel, salida, format);
		this.setNombre(nombre);
	}
	
	// TODO utilizarlo en LoggerBuilder
	public void setNombre (String nombre){
		this.nombre = nombre;
	}
	
	public void setFormato(String formato, String separador) {
		this.formato.setFormato(formato, separador);
	}
	
	// metodo queda para no romper las pruebas
	public void logear(Niveles nivel, String message) {
		logear(nivel, message, DEFAULT_NAME_LOGGER);
	}
	
	public void logear(Niveles nivel, String message, String nombreLogger) {
		// TODO utilizar un array de filtros
		if (this.nivel.compareTo(nivel) <= 0 && this.nombre == nombreLogger) {
			message = formato.darFormato(message, this.nivel);
			out.out(message);
		}
	}
	
	public Niveles getNivel() {
		return this.nivel;
	}

	public IOutput getOutput() {
		return this.out;
	}
}
