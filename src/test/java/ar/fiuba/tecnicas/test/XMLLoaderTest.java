package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import ar.fiuba.tecnicas.filter.FilterType;
import ar.fiuba.tecnicas.format.FormatType;
import ar.fiuba.tecnicas.logging.Level;
import ar.fiuba.tecnicas.logging.LoggerConfig;
import ar.fiuba.tecnicas.logging.XmlLoader;
import ar.fiuba.tecnicas.output.OutputType;

public class XMLLoaderTest {

	public static String generateTestXML() {
		String texto = "<?xml version=\"1.0\"?>"
		+ "<root>"
		+ "<logger>"
		+ "	<name>logger1</name>"
		+ "	<level>debug</level>"
		+ "	<outputs>"
		+ "		<console></console>"
		+ "	</outputs>"
		+ "	<filters>"
		+ "		<BehaveRegex>.*</BehaveRegex>"
		+ "	</filters>"
		+ "	<format>"
		+ "		<JSONFormat>%t</JSONFormat>"
		+ "	</format>"
		+ "</logger>"
		+ "<logger>"
		+ "	<name>logger2</name>"
		+ "	<level>fatal</level>"
		+ "	<outputs>"
		+ "		<console></console>"
		+ "	</outputs>"
		+ "	<separator>_</separator>"
		+ "<format>"
		+ "		<Format>%m</Format>"
		+ "	</format>"
		+ "</logger>"
		+ "<logger>"
		+ "	<name>logger3</name>"
		+ "	<level>trace</level>"
		+ "	<outputs>"
		+ "		<console></console>"
		+ "</outputs>"
		+ "</logger>"
		+ "</root>";
		return texto;
	}
	
	@Test
	public void testLoadConfiguration() throws Exception {
		InputStream stream = new ByteArrayInputStream(generateTestXML().getBytes());
		List<LoggerConfig> loggersConf = XmlLoader.loadConfiguration(stream);
		
		LoggerConfig loggerConf = loggersConf.get(0);
		assertEquals("logger1", loggerConf.getName());
		assertEquals("%t", loggerConf.getFormat());
		assertEquals(FormatType.JSONFormat, loggerConf.getFormatType());
		assertEquals("-", loggerConf.getSeparator());
		assertEquals(Level.debug,loggerConf.getLevel());
		assertEquals(FilterType.BehaveRegex,loggerConf.getFilterTypes().get(0));
		assertTrue(loggerConf.getOutputTypes().get(0) == OutputType.console);
		
		LoggerConfig loggerConf2 = loggersConf.get(1);
		assertEquals("logger2", loggerConf2.getName());
		assertEquals("_", loggerConf2.getSeparator());
		assertEquals("%m", loggerConf2.getFormat());
		assertEquals(FormatType.Format, loggerConf2.getFormatType());
	}

}
