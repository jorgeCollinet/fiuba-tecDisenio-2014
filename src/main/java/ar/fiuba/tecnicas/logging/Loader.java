package ar.fiuba.tecnicas.logging;

import java.io.IOException;
import java.util.ArrayList;

public interface Loader {
	public abstract ArrayList<LoggerConfig> loadConfiguration (String path) throws IOException;
}
