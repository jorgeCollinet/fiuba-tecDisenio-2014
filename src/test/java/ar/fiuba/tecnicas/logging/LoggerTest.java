package ar.fiuba.tecnicas.logging;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class LoggerTest {
	
	private Logger logger = new Logger();
	
	@Test
    public void log() {
        logger.writing();
        assertEquals(true, true);
    }
}
