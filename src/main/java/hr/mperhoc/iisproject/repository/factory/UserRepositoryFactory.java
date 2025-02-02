package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.UserRepository;
import hr.mperhoc.iisproject.repository.file.UserFileRepository;

public class UserRepositoryFactory {
	private static UserRepository instance;

	public static UserRepository get() {
		if (instance == null) {
			instance = new UserFileRepository();
		}

		return instance;
	}
}
