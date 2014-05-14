package ar.fiuba.tecnicas.logging;
import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.output.IOutput;

/**
 * @author Grupo3
 * 
 */

public class Logger {
	protected IOutput out;
	protected Niveles nivel;
	protected Formato formato;
	/**
	 * @param nivel nivel de log
	 * @param salida
	 */
	public Logger(Niveles nivel, IOutput salida, Formato format ) {
		// TODO terminar de implementar
		this.nivel = nivel;
		this.formato = format;
		out = salida;
	}
	
	public void setFormato(String formato, String separador)
	{
		this.formato.setFormato(formato, separador);
	}

	public void logear(Niveles nivel, String message) {
		if(this.nivel.compareTo(nivel) <= 0){
			message = formato.darFormato(message, this.nivel);
			out.out(message);
		}
	}
	
	public Niveles getNivel(){
		return this.nivel;
	}
	
	public IOutput getOutput(){
		return this.out;
	}
}
