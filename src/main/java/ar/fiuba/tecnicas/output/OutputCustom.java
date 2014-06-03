package ar.fiuba.tecnicas.output;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Clase encargada de levantar destinos custom
 * @author Grupo3
 * 
 */
public class OutputCustom implements IOutput {
	protected String className;
	
	public static OutputCustom generateOutputCustom (String className) throws Exception{
		for(Class<?> interfaces: Class.forName(className).getInterfaces()){
			if(interfaces.getClass().isInstance(IOutput.class)){
				return new OutputCustom(className);
			}
		}
		throw new Exception("nombre de clase: "+className+" no implementa interfaz IOutput\n");
	}
	
	protected OutputCustom(String className) {
		this.className = className;
	}
	
	@Override
	public void out(String message) {
		try {
			try {
				Constructor<?> constructor = Class.forName(this.className).getConstructor();
				try {
					IOutput outputCustom = (IOutput) constructor.newInstance();
					outputCustom.out(message);					
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
