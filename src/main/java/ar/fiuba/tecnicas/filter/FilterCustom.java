package ar.fiuba.tecnicas.filter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Clase encargada de filtrar por nivel
 * @author Grupo3
 * 
 */
public class FilterCustom implements IFilter{
	protected String className;
	
	public FilterCustom(String className) {
		this.className = className;
	}
	
	@Override
	public boolean hasToLog(FilterData filterData) {
		try {
			try {
				Constructor<?> constructor = Class.forName(this.className).getConstructor();
				try {
					Object filterCustom = constructor.newInstance();
					Method method = filterCustom.getClass().getMethod("hasToLog");
					boolean hasToLog = (boolean) method.invoke(filterCustom); 
					if(hasToLog){
						return true;
					}
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
