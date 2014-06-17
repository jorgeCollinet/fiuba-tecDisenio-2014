package ar.fiuba.tecnicas.output;

import java.util.List;

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
	public static IOutput generateOutput(List<OutputType> typeList, List<String> outputList) throws Exception {
		if (typeList.size() < outputList.size())
		{
			throw new Exception("Desacuerdo entre numero de outputs y sus tipos.");
		}
		OutputContainer container = new OutputContainer();
		OutputConsole consola = new OutputConsole();
		for (int i = 0; i < outputList.size(); ++i)
		{
			if (typeList.get(i) == OutputType.console) {
				container.addOutput(consola);
			} else if (typeList.get(i) == OutputType.file) {
				String nombreDeArchivo = outputList.get(i);
				container.addOutput(new OutputFile(nombreDeArchivo));
			} else if (typeList.get(i) == OutputType.OutputClass) {
				String className = outputList.get(i);
				container.addOutput(OutputCustom.generateOutputCustom(className));
			} else {
				throw new Exception(
						"Output que intenta generar no pertenece a ningun tipo conocido, string ingresado: "
						+ outputList.get(i)
						+ "\nelemento que no pertenece a ningún tipo: "
						+ typeList.get(i) + "\n");
			}

		}
		return container;

	}

}
