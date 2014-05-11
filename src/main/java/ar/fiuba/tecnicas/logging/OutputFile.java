package ar.fiuba.tecnicas.logging;
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
	public void out(String mensaje) {
		// TODO
		// abrir archivo
		// escribir archivo
		// cerrar archivo
	}

}
