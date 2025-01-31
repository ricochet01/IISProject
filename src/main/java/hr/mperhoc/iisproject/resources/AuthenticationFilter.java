package hr.mperhoc.iisproject.resources;

import java.io.IOException;
import java.security.Principal;

import hr.mperhoc.iisproject.auth.Secured;
import hr.mperhoc.iisproject.auth.token.JwtTokenUtils;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	private static final String REALM = "iisproject";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Validate the Authorization header
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}

		// Extract the token from the Authorization header
		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

		try {
			// Validate the token
			validateToken(token);
			String username = "test"; // TODO: find username based on token

			final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
			requestContext.setSecurityContext(new SecurityContext() {
				@Override
				public Principal getUserPrincipal() {
					return () -> username;
				}

				@Override
				public boolean isUserInRole(String role) {
					return true;
				}

				@Override
				public boolean isSecure() {
					return currentSecurityContext.isSecure();
				}

				@Override
				public String getAuthenticationScheme() {
					return AUTHENTICATION_SCHEME;
				}
			});
		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null && authorizationHeader.startsWith(AUTHENTICATION_SCHEME + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {
		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"").build());
	}

	private void validateToken(String token) throws Exception {
		if (!JwtTokenUtils.parseJwt(token)) throw new Exception("Invalid token!");
	}

}
