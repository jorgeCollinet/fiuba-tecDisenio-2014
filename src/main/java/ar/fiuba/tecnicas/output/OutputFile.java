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
	protected String fileName;
	protected File file;

	public OutputFile(String fileName) {
		this.fileName = fileName;
		file = new File(fileName);
		
	}

	protected void createEmptyFileIfNecesary(String fileName)
			throws IOException {
		
		if (!file.isFile() && !file.createNewFile()) {
			throw new IOException("no se pudo crear el archivo:" + fileName);
		}
	}

	@Override
	public void out(String message) {
		
		// TODO ver nombre de log harcodeado
		try {
			createEmptyFileIfNecesary(message);
			FileOutputStream is = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			w.write(message);
			w.close();

		} catch (IOException e) {
			System.err.println("Problem writing to the file" + fileName + "\n");
		}
	}

}
