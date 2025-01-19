package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Car;
import hr.mperhoc.iisproject.model.list.CarList;

public interface CarRepository {

	void add(Car c);

	void update(int id, Car c);

	void delete(int id);

	Car get(int id);

	CarList getAll();
}
