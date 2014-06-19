package ar.fiuba.tecnicas.format.subformat;

import ar.fiuba.tecnicas.format.SubformatParameters;
import ar.fiuba.tecnicas.format.Subformat;

/**
 * Subformato que devuelve el nivel del mensaje a logear
 */
public class SubformatLevel extends Subformat {

	public SubformatLevel(String match) {
		super(match);
	}

	@Override
	public String giveFormat(SubformatParameters parameters) {
		return parameters.getLevel();
	}

	@Override
	public String getJSONTag() {
		return "level";
	}
}
