package ar.fiuba.tecnicas.logging;

public class OutputBuilder {

	protected static String fileName;

	protected OutputBuilder() {
	}

	/**
	 * metodo que deberia sacar y buscar una solucion mas prolija
	 * 
	 * @param fileName
	 */
	// FIXME pasar a una forma mas linda
	public static void setOutputFileName(String fileName) {
		OutputBuilder.fileName = fileName;
	}

	/**
	 * 
	 * @param outputType
	 * @return
	 * @throws Exception
	 */
	public static IOutput generateOutput(String outputStringValues)
			throws Exception {
		boolean exito = false;
		OutputContainer container = new OutputContainer();
		if (outputStringValues.contains(OutputType.console.toString())) {
			container.addOutput(new OutputConsole());
			exito = true;

		}
		if (outputStringValues.contains(OutputType.file.toString())) {
			container.addOutput(new OutputFile(fileName));
			exito = true;
		}
		if (exito = false) {
			throw new Exception(
					"Output que intenta generar no pertenece a ningun tipo conocido, valor ingresado: "
							+ outputStringValues);
		}
		return container;
	}

}
