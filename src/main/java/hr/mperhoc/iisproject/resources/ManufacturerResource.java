package hr.mperhoc.iisproject.resources;

import hr.mperhoc.iisproject.model.Manufacturer;
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

@Path("/manufacturers")
public class ManufacturerResource {

	@GET
	public String getAll() {
		return XMLUtils.toXml(ManufacturerRepositoryFactory.get().getAll());
	}

	@GET
	@Path("/{id}")
	public String getById(@PathParam("id") int id) {
		return XMLUtils.toXml(ManufacturerRepositoryFactory.get().get(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addManufacturer(String content) {
		// Still basic code, have to add error handling
		Manufacturer manufacturer = XMLUtils.fromXml(content, Manufacturer.class);
		ManufacturerRepositoryFactory.get().add(manufacturer);

		return Response.ok(content).build();
	}

}
