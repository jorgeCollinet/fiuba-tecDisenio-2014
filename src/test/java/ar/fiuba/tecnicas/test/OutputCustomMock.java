package ar.fiuba.tecnicas.test;

import ar.fiuba.tecnicas.output.IOutput;

/**
 * Clase encargada de escribir en consola
 * 
 * @author Grupo3
 * 
 */
public class OutputCustomMock implements IOutput {
	@Override
	public void out(String mensaje) {
		System.out.print(mensaje + "\n");
	}
}
