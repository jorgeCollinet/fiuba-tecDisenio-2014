package ar.fiuba.tecnicas.output;

/**
 * Clase encargada de escribir en consola
 * 
 * @author Grupo3
 * 
 */
public class OutputConsole implements IOutput {

	@Override
	public void out(String mensaje) {
		System.out.print(mensaje + "\n");
	}
}
