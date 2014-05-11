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
		/*try {
			File statText = new File("log.txt");
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			w.write(message);
			w.close();
			
			
		} catch (IOException e) {
			System.err.println("Problem writing to the file statsTest.txt");
		}*/
		out.out(message);
	}
}
