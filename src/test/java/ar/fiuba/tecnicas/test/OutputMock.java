package ar.fiuba.tecnicas.test;

import ar.fiuba.tecnicas.output.IOutput;

/**
 * Clase utilizada como mock para emular los distintos tipos de output
 * @author Grupo3
 *
 */
public class OutputMock implements IOutput {

	protected String buffer;

	public OutputMock() {
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
