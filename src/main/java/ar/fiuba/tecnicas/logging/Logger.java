package ar.fiuba.tecnicas.logging;
import ar.fiuba.tecnicas.formato.Formato;

/**
 * @author Grupo3
 * 
 */

public class Logger {
	protected IOutput out;
	protected Niveles nivel;
	protected Formato formato;
	/**
	 * @param nivel nivel de log
	 * @param salida
	 */
	public Logger(Niveles nivel, IOutput salida, Formato format ) {
		// TODO terminar de implementar
		this.nivel = nivel;
		this.formato = format;
		out = salida;
	}
	
	public void setFormato(String formato, String separador)
	{
		this.formato.setFormato(formato, separador);
	}

	public void logear(Niveles nivel, String message) {
		// TODO aca va logica de seleccion de nivel
		
		// TODO aca va logica de parseo
		message = formato.darFormato(message, nivel);
		out.out(message);
	}
}
