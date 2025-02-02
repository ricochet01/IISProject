package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.CountryRepository;
import hr.mperhoc.iisproject.repository.file.CountryFileRepository;

public class CountryRepositoryFactory {
	private static CountryRepository instance;

	public static CountryRepository get() {
		if (instance == null) {
			instance = new CountryFileRepository();
		}
		return instance;
	}
}
