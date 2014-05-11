package ar.fiuba.tecnicas.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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
		// TODO aca va logica de parseo
		out.out(message);
	}
}
