package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

import ar.fiuba.tecnicas.filter.*;
import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.output.IOutput;

/**
 * Clase encargada de verificar si el mensaje que recibe corresponde o no con su
 * nivel y enviarle el mensaje formateado mediante Formato a alguna clase que
 * implemente la interfaz IOutput
 * 
 * @author Grupo3
 * 
 */

public class Logger {
	protected IOutput out;
	protected Niveles nivel;
	protected Formato formato;
	protected String nombre;
	public static final String DEFAULT_NAME_LOGGER = "";
	private ArrayList<IFilter> filters = new ArrayList<IFilter>();

	/**
	 * @param nivel
	 *            nivel de log
	 * @param salida
	 */
	public Logger(Niveles nivel, IOutput salida, Formato format) {
		this.nivel = nivel;
		this.formato = format;
		out = salida;
		this.nombre = DEFAULT_NAME_LOGGER;
	}

	public Logger(String nombre, Niveles nivel, ArrayList<IFilter> filters, IOutput salida, Formato format) {
		this.nivel = nivel;
		this.formato = format;
		out = salida;
		this.setNombre(nombre);
		this.filters = filters;
	}
	
	public Logger(String nombre,Niveles nivel, IOutput salida, Formato format){
		this(nivel, salida, format);
		this.setNombre(nombre);
	}
	
	public void setNombre (String nombre){
		this.nombre = nombre;
	}
	
	public void setFormato(String formato, String separador) {
		this.formato.setFormato(formato, separador);
	}
	
	// metodo queda para no romper las pruebas
	public void logear(Niveles nivel, String message) {
		logear(nivel, message, DEFAULT_NAME_LOGGER);
	}
	
	public void logear(Niveles nivel, String message, String nombreLogger) {
		
		FilterData filterData = new FilterData(nivel, nombreLogger, message);
		for (IFilter filter : this.filters) {
			if(!filter.hasToLog(filterData)){
				// TODO sacar estos prints al final
				/*
					System.out.print("\nnegativo: \n");
					System.out.print("datos de logger: "+this.nivel.toString()+" nombre:\""+this.nombre+"\"\n");
					System.out.print("datos de entrada: "+nivel.toString()+" nombre:\""+nombreLogger+"\"mensaje: "+message+"\n");
					System.out.print("calse de filtro: "+filter.getClass()+"\n");
				}*/
				return;
			}
		}
		message = formato.darFormato(message, this.nivel, this.nombre);
		out.out(message);
		
	}
	
	public Niveles getNivel() {
		return this.nivel;
	}
	
	public IOutput getOutput() {
		return this.out;
	}
	
	public void addFilter(IFilter filter){
		this.filters.add(filter);
	}
	
	public void setFilters(ArrayList<IFilter> filters){
		this.filters = filters;
	}
}
