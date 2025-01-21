package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Food;
import hr.mperhoc.iisproject.model.list.FoodList;

public class FoodRepositoryImpl implements FoodRepository {
	private FoodList cars;

	public FoodRepositoryImpl() {
		cars = new FoodList();
	}

	@Override
	public void add(Food c) {
		cars.add(c);
	}

	@Override
	public void update(int id, Food c) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Food get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodList getAll() {
		return cars;
	}

}
