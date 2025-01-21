package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Food;
import hr.mperhoc.iisproject.model.list.FoodList;

public interface FoodRepository {

	void add(Food c);

	void update(int id, Food c);

	void delete(int id);

	Food get(int id);

	FoodList getAll();
}
