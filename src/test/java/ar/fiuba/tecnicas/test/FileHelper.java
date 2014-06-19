package ar.fiuba.tecnicas.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase utilizada para la lectura de archivos
 * @author Grupo3
 *
 */
public class FileHelper {

	/**
	 * Clase utilizada para leer el ultimo mensaje loggueado en un archivo especifico
	 * @author Grupo3
	 *
	 */
	public static String getLastMessageLogged(String fileName) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		try {
	    	String currentLine;
	        String lastLine = "";

	        while ((currentLine = file.readLine()) != null){
	            lastLine = currentLine;
	        }

	        return lastLine;
	    } finally {
	        file.close();
	    }
	}
	
	
	
}
