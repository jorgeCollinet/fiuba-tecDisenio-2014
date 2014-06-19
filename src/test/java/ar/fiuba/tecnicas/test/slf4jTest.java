package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.logging.Level;
import ar.fiuba.tecnicas.logging.Log;
import ar.fiuba.tecnicas.logging.LoggerConfig;
import ar.fiuba.tecnicas.output.OutputType;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.impl.StaticLoggerBinder;

public class slf4jTest {
	private String nombreLogger = "testLogger";
	private ILoggerFactory factory;
	private ByteArrayOutputStream outputConsola;
	private PrintStream viejaConsola = System.out;

	@Before
	public void setUp() throws Exception {
		LoggerConfig loggerConfig = new LoggerConfig();
		loggerConfig.setName(nombreLogger);
		loggerConfig.setLevel(Level.debug);
		loggerConfig.setFormat("%m", FormatType.Format);
		loggerConfig.setSeparator(Format.defaultSeparator);
		loggerConfig.addOutput(null, OutputType.console);

		ArrayList<LoggerConfig> loggerConfigList = new ArrayList<>();
		loggerConfigList.add(loggerConfig);

		Log.loadConfiguration(loggerConfigList);
		factory = new StaticLoggerBinder().getLoggerFactory();

		outputConsola = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputConsola, true);
		System.setOut(printStream);
	}

	@After
	public void tearDown() {
		System.setOut(viejaConsola);
	}

	@Test
	public void checkNivel() {
		Logger logger = factory.getLogger(nombreLogger);
		assertTrue(logger.isDebugEnabled());
		assertFalse(logger.isTraceEnabled());
	}
	
	@Test
	public void logError() {
		Logger logger = factory.getLogger(nombreLogger);
		logger.error("Test");
		assertEquals("Test", outputConsola.toString().trim());
	}
	
	@Test
	public void noLogTrace() {
		Logger logger = factory.getLogger(nombreLogger);
		logger.trace("Test");
		assertEquals("", outputConsola.toString().trim());
	}

	@Test
	public void logDebug() {
		Logger logger = factory.getLogger(nombreLogger);
		logger.debug("mensaje");
		assertEquals("mensaje", outputConsola.toString().trim());

	}
	
	@Test
	public void logInfo() {
		Logger logger = factory.getLogger(nombreLogger);
		logger.info("mensaje");
		assertEquals("mensaje", outputConsola.toString().trim());
	}
	
	@Test
	public void logWarn() {
		Logger logger = factory.getLogger(nombreLogger);
		logger.warn("mensaje");
		assertEquals("mensaje", outputConsola.toString().trim());
	}
	
	@Test
	public void logDebugWithArgs2(){
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		String arg2 = "segunda parte del mensaje";
		logger.debug(arg0,arg1,arg2);
		assertEquals(arg1+"-"+arg2, outputConsola.toString().trim());
	}

	@Test
	public void noLogTraceWithArgs2() {
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		String arg2 = "segunda parte del mensaje";
		logger.trace(arg0,arg1,arg2);
		assertEquals("", outputConsola.toString().trim());
	}

	@Test
	public void logInfoWithArgs2() {
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		String arg2 = "segunda parte del mensaje";
		logger.info(arg0,arg1,arg2);
		assertEquals(arg1+"-"+arg2, outputConsola.toString().trim());
	}

	@Test
	public void logErrorWithArgs2() {
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		String arg2 = "segunda parte del mensaje";
		logger.error(arg0,arg1,arg2);
		assertEquals(arg1+"-"+arg2, outputConsola.toString().trim());
	}
	@Test
	public void logDebugWithArgs(){
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		logger.debug(arg0,arg1);
		assertEquals(arg0+","+arg1, outputConsola.toString().trim());
	}

	@Test
	public void noLogTraceWithArgs() {
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		logger.trace(arg0, arg1);
		assertEquals("", outputConsola.toString().trim());
	}

	@Test
	public void logInfoWithArgs() {
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		logger.info(arg0, arg1); 
		assertEquals(arg0 + "," + arg1, outputConsola.toString().trim());
	}

	@Test
	public void logErrorWithArgs() {
		Logger logger = factory.getLogger(nombreLogger);
		String arg0 = "mensaje";
		String arg1 = "segunda parte del mensaje";
		logger.error(arg0, arg1);
		assertEquals(arg0 + "," + arg1, outputConsola.toString().trim());
	}
	
	@Test
	public void logDebugWithException() {
		Logger logger = factory.getLogger(nombreLogger);
		Throwable thro = new Throwable("descripcion excepcion");
		logger.debug("", thro);
		assertEquals(throwableToString(thro), outputConsola.toString().trim());
	}

	@Test
	public void logErrorWithException() {
		Logger logger = factory.getLogger(nombreLogger);
		Throwable thro = new Throwable("descripcion excepcion");
		logger.error("", thro);
		assertEquals(throwableToString(thro), outputConsola.toString().trim());
	}

	@Test
	public void logInfoWithException() {
		Logger logger = factory.getLogger(nombreLogger);
		Throwable thro = new Throwable("descripcion excepcion");
		logger.info("", thro);
		assertEquals(throwableToString(thro), outputConsola.toString().trim());
	}

	@Test
	public void NoLogTraceWithException() {
		Logger logger = factory.getLogger(nombreLogger);
		Throwable thro = new Throwable("descripcion excepcion");
		logger.trace("", thro);
		assertEquals("", outputConsola.toString().trim());
	}

	@Test
	public void logWarnWithException() {
		Logger logger = factory.getLogger(nombreLogger);
		Throwable thro = new Throwable("descripcion excepcion");
		logger.warn("", thro);
		assertEquals(throwableToString(thro), outputConsola.toString().trim());
	}
	
	@Test
	public void getName() {
		Logger logger = factory.getLogger(nombreLogger);
		assertEquals(nombreLogger, logger.getName());

	}

	@Test
	public void isDebugEnabled() {
		Logger logger = factory.getLogger(nombreLogger);
		assertTrue(logger.isDebugEnabled());
	}

	@Test
	public void isErrorEnabled() {
		Logger logger = factory.getLogger(nombreLogger);
		assertTrue(logger.isErrorEnabled());
	}

	@Test
	public void isInfoEnabled() {
		Logger logger = factory.getLogger(nombreLogger);
		assertTrue(logger.isInfoEnabled());
	}

	@Test
	public void isNotTraceEnabled() {
		Logger logger = factory.getLogger(nombreLogger);
		assertFalse(logger.isTraceEnabled());
	}

	@Test
	public void isWarnEnabled() {
		Logger logger = factory.getLogger(nombreLogger);
		assertTrue(logger.isWarnEnabled());
	}

	/**
	 * auxiliar de prebas que permite el mismo parceo de excepciones que Log
	 * 
	 * @param throwable
	 * @return
	 */
	private static String throwableToString(Throwable throwable) {
		String result = "";
		if (throwable.getMessage() != null)
			result += throwable.getMessage() + ":\n";
		result += "Stacktrace:\n";
		for (StackTraceElement e : throwable.getStackTrace()) {
			result += "At line " + e.getLineNumber() + " in "
					+ e.getMethodName() + " in file " + e.getFileName() + "\n";
		}
		return result.substring(0,result.length()-1);
	}
	
	/* 
	 * prototipo para el testeo de todos los metodos similares
	@Ignore
	@Test
	public void aux() {
		Object obj = factory.getLogger(nombreLogger);
		Method[] metodos = obj.getClass().getDeclaredMethods();

		java.util.List<java.util.Map.Entry<String, Boolean>> metodosAplicables = new java.util.ArrayList<>();
		Entry<String, Boolean> pair1 = new java.util.AbstractMap.SimpleEntry<>("warn", true);
		Entry<String, Boolean> pair2 = new java.util.AbstractMap.SimpleEntry<>("debug", true);
		Entry<String, Boolean> pair3 = new java.util.AbstractMap.SimpleEntry<>("error", true);
		Entry<String, Boolean> pair4 = new java.util.AbstractMap.SimpleEntry<>("info", true);
		Entry<String, Boolean> pair5 = new java.util.AbstractMap.SimpleEntry<>("trace", false);
		Entry<String, Boolean> pair6 = new java.util.AbstractMap.SimpleEntry<>("isDebugEnabled", true);
		Entry<String, Boolean> pair7 = new java.util.AbstractMap.SimpleEntry<>("isWarnEnabled", true);
		Entry<String, Boolean> pair8 = new java.util.AbstractMap.SimpleEntry<>("isInfoEnabled", true);
		Entry<String, Boolean> pair9 = new java.util.AbstractMap.SimpleEntry<>("isErrorEnabled", true);
		Entry<String, Boolean> pair10 = new java.util.AbstractMap.SimpleEntry<>("isTraceEnabled", false);

		metodosAplicables.add(pair1);
		metodosAplicables.add(pair2);
		metodosAplicables.add(pair3);
		metodosAplicables.add(pair4);
		metodosAplicables.add(pair5);
		metodosAplicables.add(pair6);
		metodosAplicables.add(pair7);
		metodosAplicables.add(pair8);
		metodosAplicables.add(pair9);
		metodosAplicables.add(pair10);

		for (Method me : metodos) {
			outputConsola = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(outputConsola, true);
			System.setOut(printStream);
			
			String nameMethod = getMethodName(me.toString());
			String methodParameters = getMethodParameters(me.toString());

			for (Entry<String, Boolean> pair : metodosAplicables) {
				if (pair.getKey().contains(nameMethod)) {
					try {
						if (methodParameters.equals("java.lang.String")) {
							me.invoke(obj, "mensaje");
							assertConsole(me.toString(),"mensaje",pair.getValue());			
						}
						else if (methodParameters.equals("java.lang.String,java.lang.Object")){
							String arg1 = "mensaje";
							String arg2 = "segunda parte del mensaje";
							me.invoke(obj, arg1,arg2);
							assertConsole(me.toString(),arg1+","+arg2,pair.getValue());
						}
						else if (methodParameters.equals("java.lang.String,java.lang.Object[]")){
							String arg1 = "mensaje";
							String[] arg2 = {"segunda parte del mensaje"};
							me.invoke(obj, arg1,arg2);
							assertConsole(me.toString(),arg1+","+arg2[0],pair.getValue());
						}
						else if (methodParameters.equals("java.lang.String,java.lang.Throwable")) {
							String arg1 = "mensaje";
							Throwable thro = new Throwable("descripcion excepcion");
							me.invoke(obj, arg1, thro);
							
							assertConsole(me.toString(),throwableToString(thro),pair.getValue());
						}
						else if (methodParameters.equals("")){
							viejaConsola.println(nameMethod + ":"+ methodParameters);
							assertEquals(pair.getValue(), me.invoke(obj));
						}
					} catch (Exception e) {
						e.printStackTrace(viejaConsola);
						viejaConsola.println("error: "+nameMethod+":"+methodParameters);
					}
				}
			}
		}
	}
	
	private String getMethodParameters(String methodSignature) {
		int from = methodSignature.indexOf("(");
		int to = methodSignature.indexOf(")");
		return methodSignature.substring(from + 1, to);
	}

	private String getMethodName(String methodSignature) {
		int to = methodSignature.indexOf("(");
		String nameMethod = methodSignature.substring(0, to);
		int from = nameMethod.lastIndexOf(".");
		nameMethod = nameMethod.substring(from + 1);
		return nameMethod;
	}
	
	 * auxiliar de pruebas que comprueba si se escribio o no en la consola
	 * 
	 * @param string
	 * @param hasToLog
	 *            si recibe false, el mensaje deberia ser en blanco
	 *
	private void assertConsole(String errorMesaje, String string, Boolean hasToLog) {
		String message = "";
		if (hasToLog) {
			message = string;
		}
		assertEquals(errorMesaje, message, outputConsola.toString().trim());
	}
	*/
}
