package ar.fiuba.tecnicas.output;


/**
 * @author Grupo3
 * 
 */
public class OutputConsole implements IOutput {

	@Override
	public void out(String mensaje) {
		System.out.print(mensaje);
	}
}
