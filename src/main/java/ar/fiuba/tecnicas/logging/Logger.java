package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

import ar.fiuba.tecnicas.filter.*;
import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.output.IOutput;

/**
 * Clase encargada de verificar si el mensaje que recibe corresponde o no con su
 * nivel mediante el uso de los filtros que implementa IFilter y enviarle el
 * mensaje formateado mediante Formato a alguna clase que implemente la interfaz
 * IOutput
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
	 * 
	 * @param nombre nombre del logger
	 * @param nivel nivel dellogger
	 * @param filters filtros que utilizará para verificar si corresponde o no logear
	 * @param salida salida que utilizará el logger para loggear
	 * @param format formato que utilizara el logger para logear
	 */
	public Logger(String nombre, Niveles nivel, ArrayList<IFilter> filters,
			IOutput salida, Formato format) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.filters = filters;
		this.out = salida;
		this.formato = format;
	}
	/**
	 * Este constructor es similar al anterior salvo que pone su nombre como default
	 * @param nivel
	 * @param filters
	 * @param salida
	 * @param format
	 */
	public Logger(Niveles nivel, ArrayList<IFilter> filters, IOutput salida, Formato format) {
		this(Logger.DEFAULT_NAME_LOGGER.toString(), nivel, filters, salida,format);
	}
	/**
	 *Iintenta logear (si le corresponde) con nombre default
	 * @param nivel
	 * @param message
	 */
	public void logear(Niveles nivel, String message) {
		logear(nivel, message, DEFAULT_NAME_LOGGER);
	}
	/**
	 * Intenta loggear (si le corresponde) con nombre ingresado
	 * @param nivel
	 * @param message
	 * @param nombreLogger
	 */
	public void logear(Niveles nivel, String message, String nombreLogger) {

		FilterData filterData = new FilterData(nivel, nombreLogger, message);
		for (IFilter filter : this.filters) {
			if (!filter.hasToLog(filterData)) {
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
}
