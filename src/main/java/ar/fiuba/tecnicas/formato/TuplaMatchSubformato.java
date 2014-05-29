package ar.fiuba.tecnicas.formato;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.invoke.*;

/**
 * Una tupla que contiene un patrón (expresion regular)aparejado con una
 * referencia a función que devuelve un subformato (constructor)
 */
public class TuplaMatchSubformato 
{
	private MethodHandle refConstructor;
	private Pattern patron;

	public TuplaMatchSubformato(String patron, Class<? extends Subformato> clase) 
	{
		this.patron = Pattern.compile(patron);
		try 
		{
			refConstructor = MethodHandles.lookup().findConstructor(clase, MethodType.methodType(void.class, String.class));
		}
		// No deberia entrar nunca a este catch porque el tipo esta chequeado
		catch (IllegalAccessException | NoSuchMethodException e)  
		{
			System.out.println("Error de programación al crear TuplaMatchSubformato.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve un Matcher para matchear contra el patron interno de la tupla
	 * @param input		Secuencia de caracteres donde buscar el patron
	 * @return			El matcher
	 */
	public Matcher getMatcher(CharSequence input)
	{
		return patron.matcher(input);
	}
	
	/**
	 * Devuelve una nueva instancia de una subclase de Subformato
	 * @param match		String que se matcheo
	 * @return			Nueva instancia de subformato
	 */
	public Subformato getNewSubformato(String match)
	{
		try 
		{
			return (Subformato) refConstructor.invoke(match);
		} 
		// No deberia entrar nunca a este catch porque el tipo estaba chequeado
		catch (Throwable e)
		{
			System.out.println("Error de programación al ejecutar TuplaMatchSubformato.");
			e.printStackTrace();
			return null;
		}
	}

}
