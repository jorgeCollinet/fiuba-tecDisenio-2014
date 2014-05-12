package ar.fiuba.tecnicas.logging;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LogTest {

	@Test
	public void testLoadConfiguration() throws Exception{
		Log.loadConfiguration("propertiesLog.txt");
		assertEquals(true,true);
	}

}
