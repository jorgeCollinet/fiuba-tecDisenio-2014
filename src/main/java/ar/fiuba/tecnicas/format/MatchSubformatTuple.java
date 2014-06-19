package ar.fiuba.tecnicas.format;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.Constructor;

/**
 * Una tupla que contiene un patrón (expresion regular)aparejado con una
 * referencia a función que devuelve un subformato (constructor)
 */
public class MatchSubformatTuple {
	private Pattern patron;
	private Constructor<? extends Subformat> refConstructor;

	public MatchSubformatTuple(String pattern, Class<? extends Subformat> aClass) {
		this.patron = Pattern.compile(pattern);
		try {
			refConstructor = aClass.getConstructor(String.class);
		}
		// No deberia entrar nunca a este catch porque la herencia esta
		// chequeada
		catch (Throwable e) {
			System.out
					.println("Error de programación al crear MatchSubformatTuple.");
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve un Matcher para matchear contra el patron interno de la tupla
	 * 
	 * @param input
	 *            Secuencia de caracteres donde buscar el patron
	 * @return El matcher
	 */
	public Matcher getMatcher(CharSequence input) {
		return patron.matcher(input);
	}

	/**
	 * Devuelve una nueva instancia de una subclase de Subformato
	 * 
	 * @param match
	 *            String que se matcheo
	 * @return Nueva instancia de subformato
	 */
	public Subformat getNewSubformat(String match) {
		try {
			return refConstructor.newInstance(match);
		}
		// No deberia entrar nunca a este catch porque la herencia esta
		// chequeada
		catch (Throwable e) {
			System.out.println("Error de programación al ejecutar TuplaMatchSubformato.");
			e.printStackTrace();
			return null;
		}
	}

}
