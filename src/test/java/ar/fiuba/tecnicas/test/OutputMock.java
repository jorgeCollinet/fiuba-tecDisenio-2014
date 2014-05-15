package ar.fiuba.tecnicas.test;

import ar.fiuba.tecnicas.output.IOutput;


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
