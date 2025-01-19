package hr.mperhoc.iisproject.resources;

import hr.mperhoc.iisproject.auth.Secured;
import hr.mperhoc.iisproject.model.Car;
import hr.mperhoc.iisproject.model.CarManufacturer;
import hr.mperhoc.iisproject.model.list.CarManufacturersList;
import hr.mperhoc.iisproject.repository.factory.CarManufacturerRepositoryFactory;
import hr.mperhoc.iisproject.repository.factory.CarRepositoryFactory;
import hr.mperhoc.iisproject.xml.XMLUtils;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cars")
public class CarResource {

	@GET
	@Secured
	public Response getAll() {
		return Response.status(Response.Status.CREATED).entity(XMLUtils.toXml(CarRepositoryFactory.get().getAll()))
				.build();
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") int id) {
		return Response.status(Response.Status.OK).entity(XMLUtils.toXml(CarRepositoryFactory.get().get(id))).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addCar(String content) {
		// Still basic code, have to add error handling
		Car car = XMLUtils.fromXml(content, Car.class);
		CarRepositoryFactory.get().add(car);

		// Checking if the manufacturer exists
		CarManufacturersList manufacturers = CarManufacturerRepositoryFactory.get().getAll();
		boolean exists = false;
		for (CarManufacturer m : manufacturers.getManufacturers()) {
			if (m.getName().contentEquals(car.getManufacturer().getName())) {
				exists = true;
				break;
			}
		}

		if (!exists) CarManufacturerRepositoryFactory.get().add(car.getManufacturer());

		return Response.ok().build();
	}

}
