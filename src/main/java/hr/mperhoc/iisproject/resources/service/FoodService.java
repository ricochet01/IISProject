package hr.mperhoc.iisproject.resources.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class FoodService {
	public FoodService() {
	}

	@WebMethod
	public String findFoodByName(String name) {
		return "Testing " + name;
	}

}
