package ar.fiuba.tecnicas.output;

import java.util.ArrayList;

/**
 * @author Grupo3
 * Clase encargada de agrupar distintos outputs
 */
public class OutputContainer implements IOutput {
	public ArrayList<IOutput> outputs = new ArrayList<IOutput>();
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

	public void addOutput(IOutput output) {
		outputs.add(output);
	}

	public void deleteOutput(IOutput output) {
		outputs.remove(output);
	}
	
	public ArrayList<IOutput> getOutputs(){
		return this.outputs;
	}

}
