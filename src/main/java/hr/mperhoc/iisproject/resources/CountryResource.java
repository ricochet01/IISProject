package hr.mperhoc.iisproject.resources;

import hr.mperhoc.iisproject.auth.Secured;
import hr.mperhoc.iisproject.model.Country;
import hr.mperhoc.iisproject.repository.factory.CountryRepositoryFactory;
import hr.mperhoc.iisproject.xml.XMLUtils;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/countries")
public class CountryResource {

	@GET
	@Secured
	public String getAll() {
		return XMLUtils.toXml(CountryRepositoryFactory.get().getAll());
	}

	@GET
	@Secured
	@Path("/{id}")
	public String getById(@PathParam("id") int id) {
		return XMLUtils.toXml(CountryRepositoryFactory.get().get(id));
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addCountry(String content) {
		// Still basic code, have to add error handling
		Country country = XMLUtils.fromXml(content, Country.class);
		CountryRepositoryFactory.get().add(country);

		return Response.ok(content).build();
	}

}
