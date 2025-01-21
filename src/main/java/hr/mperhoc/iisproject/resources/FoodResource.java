package hr.mperhoc.iisproject.resources;

import hr.mperhoc.iisproject.auth.Secured;
import hr.mperhoc.iisproject.model.Food;
import hr.mperhoc.iisproject.model.Manufacturer;
import hr.mperhoc.iisproject.model.list.ManufacturersList;
import hr.mperhoc.iisproject.repository.factory.FoodRepositoryFactory;
import hr.mperhoc.iisproject.repository.factory.ManufacturerRepositoryFactory;
import hr.mperhoc.iisproject.xml.XMLUtils;
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
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		return Response.status(Response.Status.OK).entity(XMLUtils.toXml(FoodRepositoryFactory.get().get(id))).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addFood(String content) {
		// Still basic code, have to add error handling
		Food food = XMLUtils.fromXml(content, Food.class);
		FoodRepositoryFactory.get().add(food);

		// Checking if the manufacturer exists
		ManufacturersList manufacturers = ManufacturerRepositoryFactory.get().getAll();
		boolean exists = false;
		for (Manufacturer m : manufacturers.getManufacturers()) {
			if (m.getName().contentEquals(food.getManufacturer().getName())) {
				exists = true;
				break;
			}
		}

		if (!exists) ManufacturerRepositoryFactory.get().add(food.getManufacturer());

		return Response.ok().build();
	}

}
