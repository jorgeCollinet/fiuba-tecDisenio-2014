package ar.fiuba.tecnicas.output;


public class OutputBuilder {

	protected static String  fileName;

	protected OutputBuilder() {
	}

	/**
	 * A partir de un string con un determinado formato genera los distintos
	 * tipos de outputs, dependiendo de cada output puede tener un valor
	 * asociado, ej: nombre de archivo
	 * 
	 * @param outputType
	 * @return
	 * @throws Exception
	 */
	public static IOutput generateOutput(String outputStringValues)
			throws Exception {

		OutputContainer container = new OutputContainer();
		OutputConsole consola = new OutputConsole();
		String[] list = outputStringValues.split(",");
		for (String item : list) {
			if (item.contains(OutputType.console.toString())) {
				container.addOutput(consola);
			} else if (item.contains(OutputType.file.toString())) {
				String[] tuplaFileNombreDeArchivo = item.split(":");
				container.addOutput(new OutputFile(tuplaFileNombreDeArchivo[1]));
			} else {
				throw new Exception(
						"Output que intenta generar no pertenece a ningun tipo conocido, string ingresado: "
								+ outputStringValues
								+ "\nelemento que no pertenece a ningún tipo: "
								+ item + "\n");
			}
		}
		return container;
	}

}
