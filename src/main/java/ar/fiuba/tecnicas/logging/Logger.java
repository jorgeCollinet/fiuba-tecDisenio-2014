package ar.fiuba.tecnicas.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Logger {
	public void writing() {
	    try {
	        File statText = new File("log.txt");
	        FileOutputStream is = new FileOutputStream(statText);
	        OutputStreamWriter osw = new OutputStreamWriter(is);    
	        Writer w = new BufferedWriter(osw);
	        w.write("POTATO!!!");
	        w.close();
	    } catch (IOException e) {
	        System.err.println("Problem writing to the file statsTest.txt");
	    }
	}
}
