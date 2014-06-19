package ar.fiuba.tecnicas.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlLoader {

	public static List<LoggerConfig> loadConfiguration(String path)
			throws Exception {
		return loadConfiguration(new FileInputStream(new File(path)));
	}

	public static List<LoggerConfig> loadConfiguration(InputStream input) {
		ArrayList<LoggerConfig> loggersConf = new ArrayList<>();
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(input);
			doc.getDocumentElement().normalize();
			NodeList loggers = doc.getElementsByTagName("logger");
			for (int i = 0; i < loggers.getLength(); ++i) {
				LoggerConfig config = new LoggerConfig();
				parseLoggerNode(loggers.item(i), config);
				loggersConf.add(config);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Error al parsear XML: " + e.getMessage());
			e.printStackTrace();
		}
		return loggersConf;
	}

	private static void parseLoggerNode(Node node, LoggerConfig config) {
		if (!node.hasChildNodes())
			return;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			switch (child.getNodeName()) {
			case "name":
				config.setName(child.getTextContent());
				break;
			case "level":
				config.setLevel(child.getTextContent());
				break;
			case "separator":
				config.setSeparator(child.getTextContent());
				break;
			case "outputs":
				parseOutputNode(child, config);
				break;
			case "filters":
				parseFilterNode(child, config);
				break;
			case "format":
				parseFormatNode(child, config);
				break;
			}
		}
	}

	private static void parseOutputNode(Node node, LoggerConfig config) {
		if (!node.hasChildNodes())
			return;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE)
				continue;
			config.addOutput(child.getTextContent(), child.getNodeName());
		}
	}

	private static void parseFilterNode(Node node, LoggerConfig config) {
		if (!node.hasChildNodes())
			return;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE)
				continue;
			config.addFilter(child.getTextContent(), child.getNodeName());
		}
	}

	private static void parseFormatNode(Node node, LoggerConfig config) {
		if (!node.hasChildNodes())
			return;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE)
				continue;
			config.setFormat(child.getTextContent(), child.getNodeName());
		}
	}
}
