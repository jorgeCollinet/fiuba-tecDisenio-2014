package ar.fiuba.tecnicas.logging;

public class OutputBuilder {

	protected static String fileName;
	protected OutputBuilder() {
	}
	/**
	 * metodo que deberia sacar y buscar una solucion mas prolija
	 * @param fileName
	 */
	// FIXME pasar a una forma mas linda
	public static void setOutputFileName(String fileName){
		OutputBuilder.fileName = fileName;
	}
	/**
	 * 
	 * @param outputType
	 * @return
	 * @throws Exception
	 */
	public static IOutput generateOutput(OutputType outputType) throws Exception {
		switch (outputType) {
		case console:
			return new OutputConsole();

		case file: 
			return new OutputFile(fileName);

		}
		throw new Exception(
				"Output que intenta generar no pertenece a ningun tipo conocido, valor ingresado: "
						+ outputType);
	}

}
