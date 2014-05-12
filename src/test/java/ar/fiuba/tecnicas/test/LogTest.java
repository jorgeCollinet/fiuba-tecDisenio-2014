package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ar.fiuba.tecnicas.logging.Log;

public class LogTest {

	@Test
	public void testLoadConfiguration() throws Exception{
		Log.loadConfiguration("propertiesLog.txt");
		Log.saveConfiguration();
		assertEquals(true,true);
	}

}
