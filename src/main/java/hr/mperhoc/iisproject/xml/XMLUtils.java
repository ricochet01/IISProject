package hr.mperhoc.iisproject.xml;

import java.io.StringReader;
import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XMLUtils {
	private XMLUtils() {
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

}
