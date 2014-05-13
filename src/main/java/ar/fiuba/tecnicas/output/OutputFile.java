package ar.fiuba.tecnicas.output;

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
public class OutputFile implements IOutput {
	private String fileName;

	public OutputFile(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void out(String message) {
		// TODO ver nombre de log harcodeado
		try {
			// "log.txt"
			File statText = new File(fileName);
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			w.write(message);
			w.close();

		} catch (IOException e) {
			System.err.println("Problem writing to the file statsTest.txt");
		}
	}

}
