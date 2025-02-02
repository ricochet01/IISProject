package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.FoodRepository;
import hr.mperhoc.iisproject.repository.file.FoodFileRepository;

public class FoodRepositoryFactory {
	private static FoodRepository instance;

	public static FoodRepository get() {
		if (instance == null) {
			instance = new FoodFileRepository();
		}
		return instance;
	}
}
