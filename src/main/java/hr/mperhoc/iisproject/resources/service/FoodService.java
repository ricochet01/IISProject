package hr.mperhoc.iisproject.resources.service;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import hr.mperhoc.iisproject.repository.factory.FoodRepositoryFactory;
import hr.mperhoc.iisproject.xml.XMLUtils;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class FoodService {
	public FoodService() {
	}

	@WebMethod
	@WebResult(name = "foods")
	public String findFoodByName(@WebParam(name = "name") String name) {
		// //"//food[contains(name, '" + name + "')]";
		// XML file with all Food entities
		String xmlFile = XMLUtils.toXml(FoodRepositoryFactory.get().getAll());
//		if (!XMLValidator.validateJaxb(xmlFile)) return "Error while validating!";

		String xPathExpression = String.format("//food[contains(name, '%s')]", name);
		String filteredXml = "";
		try {
			filteredXml = XMLUtils.evaluateXPath(xmlFile, xPathExpression);
		} catch (XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
		}

		System.out.println(filteredXml);

		return filteredXml;
	}

}
