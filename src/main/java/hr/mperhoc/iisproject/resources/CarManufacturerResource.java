package hr.mperhoc.iisproject.resources;

import hr.mperhoc.iisproject.model.CarManufacturer;
import hr.mperhoc.iisproject.repository.factory.CarManufacturerRepositoryFactory;
import hr.mperhoc.iisproject.xml.XMLUtils;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/manufacturers")
public class CarManufacturerResource {

	@GET
	public String getAll() {
		return XMLUtils.toXml(CarManufacturerRepositoryFactory.get().getAll());
	}

	@GET
	@Path("/{id}")
	public String getById(@PathParam("id") int id) {
		return XMLUtils.toXml(CarManufacturerRepositoryFactory.get().get(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addManufacturer(String content) {
		// Still basic code, have to add error handling
		CarManufacturer manufacturer = XMLUtils.fromXml(content, CarManufacturer.class);
		CarManufacturerRepositoryFactory.get().add(manufacturer);

		return Response.ok(content).build();
	}

}
