package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

/**
 * @author Grupo3
 */
public class Log {
	private static ArrayList<Logger> loggers;

	static void log(Enum<Niveles> nivel, String message) {
		for (Logger  logger: loggers) {
			logger.logear(message);
		}
	}
}
