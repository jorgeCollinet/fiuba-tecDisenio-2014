package ar.fiuba.tecnicas.test;

import ar.fiuba.tecnicas.output.IOutput;

public class OutputTester implements IOutput {

	protected String buffer;

	public OutputTester() {
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
