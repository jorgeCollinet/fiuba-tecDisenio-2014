package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

/**
 * @author Grupo3
 * Clase encargada de agrupar distintos outputs
 */
public class OutputContainer implements IOutput {
	public ArrayList<IOutput> outputs;
	
	/**
	 * 
	 */
	public OutputContainer() {
		// TODO ver como levantar outputs
	}

	@Override
	public void out(String mensaje) {
		for (IOutput out : outputs) {
			out.out(mensaje);
		}
	}

}
