package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Food;
import hr.mperhoc.iisproject.model.list.FoodList;

public class FoodRepositoryImpl implements FoodRepository {
	private FoodList foods;

	public FoodRepositoryImpl() {
		foods = new FoodList();
	}

	@Override
	public void add(Food f) {
		foods.add(f);
	}

	@Override
	public void update(int id, Food f) {
		foods.update(id, f);
	}

	@Override
	public void delete(int id) {
		foods.remove(id);
	}

	@Override
	public Food get(int id) {
		return foods.get(id);
	}

	@Override
	public FoodList getAll() {
		return foods;
	}

}
