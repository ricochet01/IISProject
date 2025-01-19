package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Car;
import hr.mperhoc.iisproject.model.list.CarList;

public class CarRepositoryImpl implements CarRepository {
	private CarList cars;

	public CarRepositoryImpl() {
		cars = new CarList();
	}

	@Override
	public void add(Car c) {
		cars.add(c);
	}

	@Override
	public void update(int id, Car c) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Car get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarList getAll() {
		return cars;
	}

}
