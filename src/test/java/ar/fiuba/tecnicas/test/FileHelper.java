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

	
	public static String getLastMessageLogged(String fileName) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		try {
	    	String sCurrentLine;
	        String lastLine = "";

	        while ((sCurrentLine = file.readLine()) != null){
	            lastLine = sCurrentLine;
	        }

	        return lastLine;
	    } finally {
	        file.close();
	    }
	}
	
	
	
}
