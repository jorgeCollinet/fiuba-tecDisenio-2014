package ar.fiuba.tecnicas.logging;

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
