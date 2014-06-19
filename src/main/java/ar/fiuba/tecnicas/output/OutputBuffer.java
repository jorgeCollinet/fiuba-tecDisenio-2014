package ar.fiuba.tecnicas.output;


/**
 * Clase utilizada como mock para emular los distintos tipos de output
 * @author Grupo3
 *
 */
public class OutputBuffer implements IOutput {

	protected String buffer;

	public OutputBuffer() {
		buffer = "";
	}

	@Override
	public void out(String mensaje) {
		buffer = mensaje;
	}

	public String getMessage() {
		return buffer;
	}	
}
