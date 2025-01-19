package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.CarManufacturerRepository;
import hr.mperhoc.iisproject.repository.CarManufacturerRepositoryImpl;

public class CarManufacturerRepositoryFactory {
	private static CarManufacturerRepository instance;

	public static CarManufacturerRepository get() {
		if (instance == null) {
			instance = new CarManufacturerRepositoryImpl();
		}
		return instance;
	}
}
