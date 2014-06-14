package ar.fiuba.tecnicas.output;

import java.util.ArrayList;

/**
 * Clase encargada de construir los distintos OutPuts que implementan la
 * interfaz IOutput
 * 
 * @author Grupo3
 * 
 */
public class OutputBuilder {

	protected static String fileName;
	public static String Id = "Output";

	protected OutputBuilder() {
	}

	/**
	 * A partir de un string con un determinado formato genera los distintos
	 * tipos de outputs, dependiendo de cada output puede tener un valor
	 * asociado, ej: nombre de archivo
	 * 
	 * @param arrayList
	 * @return IOutput
	 * @throws Exception
	 */
	public static IOutput generateOutput(ArrayList<String> outputList) throws Exception {
		OutputContainer container = new OutputContainer();
		OutputConsole consola = new OutputConsole();
		
		for (String item : outputList) {
			String[] subItemsList = item.split(">");
			if (item.contains(OutputType.console.toString())) {
				container.addOutput(consola);
				
			} else if (item.contains(OutputType.file.toString())) {
				String nombreDeArchivo = subItemsList[1];
				container.addOutput(new OutputFile(nombreDeArchivo));
				
			} else if (item.contains(OutputType.OutputClass.toString())) {
				String className = subItemsList[1];
				container.addOutput(OutputCustom.generateOutputCustom(className));
				
			} else {
				throw new Exception(
						"Output que intenta generar no pertenece a ningun tipo conocido, string ingresado: "
								+ outputList
								+ "\nelemento que no pertenece a ningún tipo: "
								+ item + "\n");
			}

		}
		return container;

	}

}
