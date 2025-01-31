package hr.mperhoc.iisproject.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XMLUtils {
	private static DocumentBuilder db;
	private static XPath xpath;

	private XMLUtils() {
	}

	public static void init() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		XPathFactory xpathFactory = XPathFactory.newInstance();
		xpath = xpathFactory.newXPath();
	}

	public static String toXml(Object data) {
		String xml = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(data.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// jaxbMarshaller.marshal(data, System.out);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(data, sw);
			xml = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return xml;
	}

	public static <T> T fromXml(String xml, Class<T> cls) {
		T obj = null;
		try {
			StringReader sr = new StringReader(xml);
			JAXBContext jaxbContext = JAXBContext.newInstance(cls);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			obj = cls.cast(unmarshaller.unmarshal(sr));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public static String evaluateXPath(String xml, String expression)
			throws SAXException, IOException, XPathExpressionException {
		InputSource src = new InputSource(new StringReader(xml.trim()));

		// Creating a DOM Document object to evaluate the XPath over
		Document document = db.parse(src);
		XPathExpression expr = xpath.compile(expression);
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

		// Build the result with tag names and content
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			result.append(node.getTextContent().trim());
			if (i == nodes.getLength() - 1) result.append('\n');
		}

		return result.toString().trim();
	}

}
