package hr.mperhoc.iisproject.resources;

import hr.mperhoc.iisproject.auth.Secured;
import hr.mperhoc.iisproject.model.Country;
import hr.mperhoc.iisproject.model.Food;
import hr.mperhoc.iisproject.model.Manufacturer;
import hr.mperhoc.iisproject.model.list.CountryList;
import hr.mperhoc.iisproject.model.list.ManufacturerList;
import hr.mperhoc.iisproject.repository.factory.CountryRepositoryFactory;
import hr.mperhoc.iisproject.repository.factory.FoodRepositoryFactory;
import hr.mperhoc.iisproject.repository.factory.ManufacturerRepositoryFactory;
import hr.mperhoc.iisproject.xml.XMLUtils;
import hr.mperhoc.iisproject.xml.XMLValidator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/food")
public class FoodResource {

	@GET
	@Secured
	public Response getAll() {
		return Response.status(Response.Status.CREATED).entity(XMLUtils.toXml(FoodRepositoryFactory.get().getAll()))
				.build();
	}

	@GET
	@Secured
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		return Response.status(Response.Status.OK).entity(XMLUtils.toXml(FoodRepositoryFactory.get().get(id))).build();
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addFood(String content) {
		// Is the given XML invalid
		if (!XMLValidator.validateRng(content)) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}

		Food food = XMLUtils.fromXml(content, Food.class);
		FoodRepositoryFactory.get().add(food);

		// Checking if the manufacturer exists; if not, we're creating it
		checkManufacturer(food);

		// Afterwards doing the same for the country
		checkCountry(food);

		return Response.ok().build();
	}

	private void checkManufacturer(Food food) {
		ManufacturerList manufacturers = ManufacturerRepositoryFactory.get().getAll();
		boolean exists = false;
		for (Manufacturer m : manufacturers.getManufacturers()) {
			if (m.getName().contentEquals(food.getManufacturer().getName())) {
				exists = true;
				break;
			}
		}
		if (!exists) ManufacturerRepositoryFactory.get().add(food.getManufacturer());
	}

	private void checkCountry(Food food) {
		CountryList countries = CountryRepositoryFactory.get().getAll();
		boolean exists = false;
		for (Country c : countries.getCountries()) {
			if (c.getName().contentEquals(food.getCountryOfOrigin().getName())) {
				exists = true;
				break;
			}
		}
		if (!exists) CountryRepositoryFactory.get().add(food.getCountryOfOrigin());
	}

}
