package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.ManufacturerRepository;
import hr.mperhoc.iisproject.repository.file.ManufacturerFileRepository;

public class ManufacturerRepositoryFactory {
	private static ManufacturerRepository instance;

	public static ManufacturerRepository get() {
		if (instance == null) {
			instance = new ManufacturerFileRepository();
		}
		return instance;
	}
}
