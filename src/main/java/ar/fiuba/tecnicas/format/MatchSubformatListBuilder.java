package ar.fiuba.tecnicas.format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ar.fiuba.tecnicas.format.subformat.*;

public class MatchSubformatListBuilder {
	private static String formatFilePath = "formats.cfg";

	private MatchSubformatListBuilder() {

	}

	/**
	 * Carga del archivo definido como formatFilePath los matches y reemplazos
	 * 
	 * @return
	 */
	public static List<MatchSubformatTuple> loadFromFile() {
		ArrayList<MatchSubformatTuple> result = new ArrayList<MatchSubformatTuple>();
		String[] params = null;
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(formatFilePath));
			String line = input.readLine();
			while (line != null) {
				params = line.split("=");
				@SuppressWarnings("rawtypes")
				Class aClass = Class.forName(params[1]);
				if (!Subformat.class.isAssignableFrom(aClass)) {
					input.close();
					throw new ClassCastException(params[1]);
				}
				@SuppressWarnings("unchecked")
				Class<? extends Subformat> castedClass = (Class<? extends Subformat>) aClass;
				result.add(new MatchSubformatTuple(params[0], castedClass));
				line = input.readLine();
			}
			input.close();
		} catch (IOException e) {
			System.out
					.println("Error al abrir el archivo de los matches de subformato:"
							+ e.getMessage());
			return loadDefault();
		} catch (ClassNotFoundException e) {
			System.out
					.println("Error al cargar una clase de match de subformato:"
							+ e.getMessage());
			return loadDefault();
		} catch (ClassCastException e) {
			System.out
					.println("Error al cargar una clase de match de subformato.");
			System.out.println("Clase no hereda de Subformato: "
					+ e.getMessage());
			return loadDefault();
		}
		return result;
	}

	/**
	 * Devuelve una lista con los matches definidos por defecto
	 * 
	 * @return
	 */
	public static List<MatchSubformatTuple> loadDefault() {
		System.out.println("Cargando matches por defecto.");
		ArrayList<MatchSubformatTuple> result = new ArrayList<MatchSubformatTuple>();
		result.add(new MatchSubformatTuple("%%", SubformatEscape.class));
		result.add(new MatchSubformatTuple("%d\\{[^\\}]*\\}",
				SubformatDate.class));
		result.add(new MatchSubformatTuple("%m", SubformatMessage.class));
		result.add(new MatchSubformatTuple("%p", SubformatLevel.class));
		result.add(new MatchSubformatTuple("%F", SubformatFileName.class));
		result.add(new MatchSubformatTuple("%M", SubformatMethodName.class));
		result.add(new MatchSubformatTuple("%t", SubformatThreadName.class));
		result.add(new MatchSubformatTuple("%L", SubformatLineNumber.class));
		result.add(new MatchSubformatTuple("%n", SubformatSeparator.class));
		result.add(new MatchSubformatTuple("%g", SubformatLoggerName.class));
		return result;
	}
}
