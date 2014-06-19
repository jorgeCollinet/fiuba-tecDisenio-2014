package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve el numero de linea donde se llamo a darFormato tras
 * descartar los paquetes triviales en el stack de llamadas
 */
public class SubformatLineNumber extends Subformat {

	public SubformatLineNumber(String match) {
		super(match);
	}

	@Override
	public String giveFormat(SubformatParameters parameters) {
		return parameters.getLineNumber();
	}

	@Override
	public String getJSONTag() {
		return "linenumber";
	}
}
