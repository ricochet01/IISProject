package hr.mperhoc.iisproject.repository.factory;

import hr.mperhoc.iisproject.repository.FoodRepository;
import hr.mperhoc.iisproject.repository.FoodRepositoryImpl;

public class FoodRepositoryFactory {
	private static FoodRepository instance;

	public static FoodRepository get() {
		if (instance == null) {
			instance = new FoodRepositoryImpl();
		}
		return instance;
	}
}
