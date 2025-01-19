package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.UserRepository;
import hr.mperhoc.iisproject.repository.UserRepositoryImpl;

public class UserRepositoryFactory {
	private static UserRepository instance;

	public static UserRepository get() {
		if (instance == null) {
			instance = new UserRepositoryImpl();
		}

		return instance;
	}
}
