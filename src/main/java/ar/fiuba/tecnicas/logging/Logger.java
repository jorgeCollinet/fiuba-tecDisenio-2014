package ar.fiuba.tecnicas.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author Grupo3
 * 
 */

public class Logger {
	/**
	 * 
	 */
	public Logger() {
		// TODO Auto-generated constructor stub
	}
	
	public void logear(String message) {
	    try {
	        message += "\n";
	    	FileWriter filename = new FileWriter("log.txt", true);
	        filename.write(message);
	        filename.close();
	    } catch (IOException e) {
	        System.err.println("Problem writing to the file statsTest.txt");
	    }
	}
}
