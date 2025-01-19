package hr.mperhoc.iisproject.resources;

import java.util.List;

import hr.mperhoc.iisproject.auth.Credentials;
import hr.mperhoc.iisproject.model.User;
import hr.mperhoc.iisproject.repository.factory.UserRepositoryFactory;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationEndpoint {

	@Path("/register")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(Credentials credentials) {
		// Check if username/email already exists
		User user = new User(credentials);

		// TODO: Use BCrypt for passwords
		UserRepositoryFactory.get().add(user);

		return Response.status(Response.Status.CREATED).build();
	}

	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(Credentials credentials) {
		try {
			String username = credentials.getUsername();
			String password = credentials.getPassword();

			// Authenticate the user using the credentials provided
			authenticate(username, password);

			// Issue a token for the user
			String token = issueToken(credentials.getUsername());

			// Return the token on the response
			return Response.ok(token).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	private void authenticate(String username, String password) throws Exception {
		User user = findUserByUsername(username);
		if (user == null) {
			// throw new Exception("No such user with the specified username exists!");
			throw new Exception("Invalid credentials!");
		}

		// Check if passwords match
		// TODO: use BCrypt
		if (!user.getPassword().contentEquals(password)) {
			throw new Exception("Invalid credentials!");
		}

		// We're authenticated!
	}

	private User findUserByUsername(String username) {
		List<User> users = UserRepositoryFactory.get().getAll();

		for (User user : users) {
			if (user.getUsername().contentEquals(username)) return user;
		}

		return null;
	}

	private String issueToken(String username) {
		// TODO: generate JWT token
		return "";
	}

}
