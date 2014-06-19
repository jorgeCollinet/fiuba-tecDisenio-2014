package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve el nombre del thread donde se llamo a darFormato
 */
public class SubformatThreadName extends Subformat {

	public SubformatThreadName(String match) {
		super(match);
	}

	@Override
	public String giveFormat(SubformatParameters parameters) {
		return parameters.getThreadName();
	}

	@Override
	public String getJSONTag() {
		return "thread";
	}
}
