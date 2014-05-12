package ar.fiuba.tecnicas.logging;

import java.util.Properties;

public class OutputBuilder {

	protected static String fileName;

	protected OutputBuilder() {
	}

	/**
	 * 
	 * @param outputType
	 * @return
	 * @throws Exception
	 */
	public static IOutput generateOutput(String outputStringValues,Properties prop)
			throws Exception {
		fileName = prop.getProperty("logFileName", "log.txt");
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
