package hr.mperhoc.iisproject.model;

import java.io.Serializable;

import org.mindrot.jbcrypt.BCrypt;

import hr.mperhoc.iisproject.auth.Credentials;

public class User implements Serializable {
	private static final long serialVersionUID = 5509413450943968092L;
	private static int idCounter = 0;

	private final int id;
	private String email;
	private String username, password;
	// Other fields such as first name, last name, date of birth and country could
	// be added

	public User(String email, String username, String password) {
		this.id = idCounter++;
		this.email = email;
		this.username = username;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public User(Credentials credentials) {
		this(credentials.getEmail(), credentials.getUsername(), credentials.getPassword());
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return email + ";" + username;
	}
}
