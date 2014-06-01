package ar.fiuba.tecnicas.output;

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
	 * @param outputStringValues
	 * @return IOutput
	 * @throws Exception
	 */
	public static IOutput generateOutput(String outputStringValues)
			throws Exception {
		// TODO implementar nuevo parseo
		OutputContainer container = new OutputContainer();
		OutputConsole consola = new OutputConsole();
		String[] list = outputStringValues.split(",");
		for (String item : list) {
			if (item.contains("Output")) {
				String[] subItemsList = item.split(">");
				if (item.contains(OutputType.console.toString())) {
					container.addOutput(consola);
				} else if (item.contains(OutputType.file.toString())) {
					String[] tuplaFileNombreDeArchivo = subItemsList[1].split(":");
					container.addOutput(new OutputFile(tuplaFileNombreDeArchivo[1]));
				} else if (item.contains(OutputType.Class.toString())) {
					// TODO implementar las clases Output personalizadas
					
					
				} else {
					throw new Exception(
							"Output que intenta generar no pertenece a ningun tipo conocido, string ingresado: "
									+ outputStringValues
									+ "\nelemento que no pertenece a ningún tipo: "
									+ item + "\n");
				}

			}
		}
		return container;

	}

}
