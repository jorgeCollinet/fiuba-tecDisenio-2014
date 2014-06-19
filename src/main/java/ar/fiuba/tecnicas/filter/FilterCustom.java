package ar.fiuba.tecnicas.filter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Clase encargada de filtrar por nivel
 * @author Grupo3
 * 
 */
public class FilterCustom implements IFilter {
	protected String className;
	
	public static FilterCustom generateFilterCustom (String className) throws Exception{
		for(Class<?> interfaces: Class.forName(className).getInterfaces()){
			if(interfaces.getClass().isInstance(IFilter.class)){
				return new FilterCustom(className);
			}
		}
		throw new Exception("nombre de clase: "+className+" no implementa interfaz IFilter\n");
	}
	
	protected FilterCustom(String className) {
		this.className = className;
	}
	
	@Override
	public boolean hasToLog(FilterData filterData) {
		try {
			try {
				Constructor<?> constructor = Class.forName(this.className).getConstructor();
				try {
					IFilter filterCustom = (IFilter) constructor.newInstance();
					return filterCustom.hasToLog(filterData);					
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					System.out.println("Error de programación al intentar instanciar un FilterCustom.");
					return false;
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				System.out.println("Error de programación al intentar instanciar un FilterCustom.");
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error de programación al intentar instanciar un FilterCustom.");
			return false;
		}
	}
}
