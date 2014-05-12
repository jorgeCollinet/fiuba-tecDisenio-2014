package ar.fiuba.tecnicas.logging;

/**
 * @author Grupo3
 * 
 */

public class Logger {
	protected IOutput out;

	/**
	 * @param nivel nivel de log
	 * @param salida
	 */
	public Logger(Niveles nivel, IOutput salida) {
		// TODO terminar de implementar
		out = salida;
	}

	public void logear(String message) {
		// TODO aca va logica de seleccion de nivel
		// TODO aca va logica de parseo
		out.out(message);
	}
}
