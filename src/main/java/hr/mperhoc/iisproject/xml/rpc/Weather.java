package hr.mperhoc.iisproject.xml.rpc;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Weather {
	public static final String URL = "https://vrijeme.hr/hrvatska_n.xml";
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document doc;

	public Weather() {
		dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		obtainWeather();
	}

	private void obtainWeather() {
		try {
			doc = db.parse(new URI(URL).toURL().openStream());
		} catch (SAXException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		doc.normalize();
	}

	public double getTemperature(String city) {
		NodeList nodeList = doc.getElementsByTagName("Grad");
		String cityLower = city.toLowerCase();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node current = nodeList.item(i);

			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) current;
				String cityName = e.getElementsByTagName("GradIme").item(0).getTextContent();

				// We found the city
				if (cityName.toLowerCase().trim().contentEquals(cityLower)) {
					Element data = (Element) e.getElementsByTagName("Podatci").item(0);
					Node temperature = data.getElementsByTagName("Temp").item(0);

					return Double.valueOf(temperature.getTextContent().trim());
				}
			}
		}

		return Double.MIN_VALUE;
	}

}
