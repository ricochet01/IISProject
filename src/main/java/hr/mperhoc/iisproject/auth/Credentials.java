package hr.mperhoc.iisproject.auth;

import java.io.Serializable;

public class Credentials implements Serializable {
	private static final long serialVersionUID = -6964313146358863961L;
	private String email, username, password;

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

}
