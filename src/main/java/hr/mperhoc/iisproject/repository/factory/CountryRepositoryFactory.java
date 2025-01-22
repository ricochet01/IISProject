package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.CountryRepository;
import hr.mperhoc.iisproject.repository.CountryRepositoryImpl;

public class CountryRepositoryFactory {
	private static CountryRepository instance;

	public static CountryRepository get() {
		if (instance == null) {
			instance = new CountryRepositoryImpl();
		}
		return instance;
	}
}
