package ar.fiuba.tecnicas.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		try {
			createEmptyFileIfNecesary(fileName);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			out.println(message);
			out.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file" + fileName + "\n");
		}
	}

}
