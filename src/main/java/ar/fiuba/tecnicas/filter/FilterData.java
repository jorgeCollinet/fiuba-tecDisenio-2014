package ar.fiuba.tecnicas.filter;

import ar.fiuba.tecnicas.logging.Level;

/**
 * Clase utilizada para agrupar la información de un mensaje a filtrar (nombre
 * de logger, nivel, mensaje).
 * 
 * @author Grupo3
 * 
 */
public class FilterData {
	protected Level nivel;
	protected String nombre;
	protected String message;
	protected String custom;

	public FilterData(Level nivel, String nombreLogger, String message) {
		this.nivel = nivel;
		this.nombre = nombreLogger;
		this.message = message;
	}

	public Level getNivel() {
		return this.nivel;
	}

	public void setNivel(Level nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCustom() {
		return this.custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}
}
