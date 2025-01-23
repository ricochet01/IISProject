package hr.mperhoc.iisproject.xml;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidator {
	// Schema definitions
	private static SchemaFactory xsdSchemaFactory, rngSchemaFactory;
	private static URL xsdFile, rngFile;
	private static Schema xsdSchema, rngSchema;
	private static Validator xsdValidator, rngValidator;

	private XMLValidator() {
	}

	public static void init() {
		System.setProperty(SchemaFactory.class.getName() + ":" + XMLConstants.RELAXNG_NS_URI,
				"com.thaiopensource.relaxng.jaxp.XMLSyntaxSchemaFactory");

		xsdFile = XMLValidator.class.getResource("/xsd/food.xsd");
		rngFile = XMLValidator.class.getResource("/rng/food.rng");

		xsdSchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		rngSchemaFactory = SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);

		try {
			xsdSchema = xsdSchemaFactory.newSchema(xsdFile);
			rngSchema = rngSchemaFactory.newSchema(rngFile);

			xsdValidator = xsdSchema.newValidator();
			rngValidator = rngSchema.newValidator();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public static boolean validateXsd(String xml) {
		try {
			Source src = new StreamSource(new StringReader(xml));
			xsdValidator.validate(src);
			return true;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean validateRng(String xml) {
		try {
			Source src = new StreamSource(new StringReader(xml));
			rngValidator.validate(src);
			return true;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
