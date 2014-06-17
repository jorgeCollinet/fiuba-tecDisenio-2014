package ar.fiuba.tecnicas.logging;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlLoader {

	public static ArrayList<LoggerConfig> loadConfiguration(String path) {
		ArrayList<LoggerConfig> loggersConf = new ArrayList<>();
		try 
		{
			File file = new File(path);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			NodeList loggers = doc.getElementsByTagName("logger");
			for (int i = 0; i < loggers.getLength(); ++i)
			{
				LoggerConfig config = new LoggerConfig();
				Node loggerNode = loggers.item(i);
				//loggerNode.get
				
				loggersConf.add(config);
			}
		} 
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			System.out.println("Error al parsear XML: "+e.getMessage());
			e.printStackTrace();
		}

		return loggersConf;
	}

}
