package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.CarRepository;
import hr.mperhoc.iisproject.repository.CarRepositoryImpl;

public class CarRepositoryFactory {
	private static CarRepository instance;

	public static CarRepository get() {
		if (instance == null) {
			instance = new CarRepositoryImpl();
		}
		return instance;
	}
}
