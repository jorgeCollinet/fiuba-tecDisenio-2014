package ar.fiuba.tecnicas.format;

import java.util.regex.Pattern;
import ar.fiuba.tecnicas.logging.Level;

/**
 * Clase utilizada para pasar los parametros necesarios al subformato
 */
public class SubformatParameters {
	private String message;
	private Level level;
	private String separator;
	private Thread thread;
	private String logger;
	private String lineNumber;
	private String methodName;
	private String fileName;
	private boolean stackWasProcessed = false;
	private static Pattern trivialPackages = Pattern
			.compile("(^java\\..*)|(^sun\\..*)|(^org\\.junit\\..*)|"
					+ "(^ar\\.fiuba\\.tecnicas\\..*)|(^org\\.slf4j\\..*)");
	private static Pattern testPackage = Pattern.compile("^.*test.*$");

	/**
	 * Constructor
	 * 
	 * @param message
	 *            Mensaje Mensaje a logear
	 * @param level
	 *            Nivel del mensaje Nivel del mensaje
	 * @param separator
	 *            Separador Separador de campos
	 * @param thread
	 *            Thread Thread desde el cual se llama
	 */
	public SubformatParameters(String message, Level level, String separator,
			Thread thread, String logger) {
		this.message = message;
		this.level = level;
		this.separator = separator;
		this.thread = thread;
		this.logger = logger;
	}

	public String getMessage() {
		if (message == null)
			return "";
		return message;
	}

	public String getLevel() {
		if (level == null)
			return "";
		return level.toString();
	}

	public String getLogger() {
		if (logger == null)
			return "";
		return logger;
	}

	public String getSeparator() {
		return separator;
	}

	public String getThreadName() {
		return thread.getName();
	}

	public String getMethodName() {
		if (!stackWasProcessed)
			getStackData(thread);
		return methodName;
	}

	public String getFileName() {
		if (!stackWasProcessed)
			getStackData(thread);
		return fileName;
	}

	public String getLineNumber() {
		if (!stackWasProcessed)
			getStackData(thread);
		return lineNumber;
	}

	/**
	 * Dado un thread, obtiene la información de su stack tras descartar las
	 * relacionadas al paquete de login y a paquetes de java/junit/etc
	 * 
	 * @param thread
	 *            Thread a analizar
	 */
	private void getStackData(Thread thread) {
		StackTraceElement[] stack = thread.getStackTrace();
		int i = 0;
		while (i < stack.length && contrainsTrivialPackages(stack[i])) {
			++i;
		}
		if (i >= stack.length) {
			lineNumber = fileName = methodName = "Error al parsear stacktrace";
		} else {
			lineNumber = "" + stack[i].getLineNumber();
			fileName = stack[i].getFileName();
			methodName = stack[i].getMethodName();
		}
		stackWasProcessed = true;
	}

	/**
	 * Avisa si un elemento del stack pertenece a paquetes de
	 * java/sun/junit/logging
	 * 
	 * @param element
	 *            Elemento del stack
	 * @return True si el paquete de ese elemento del stack pertenece a los
	 *         nombrados
	 */
	private boolean contrainsTrivialPackages(StackTraceElement element) {
		String aClass = element.getClassName();
		return trivialPackages.matcher(aClass).matches()
				&& !testPackage.matcher(aClass).matches();
	}
}
