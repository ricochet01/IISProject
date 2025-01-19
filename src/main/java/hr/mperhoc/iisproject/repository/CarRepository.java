package hr.mperhoc.iisproject.repository;

import java.util.List;

import hr.mperhoc.iisproject.model.Car;

public interface CarRepository {

	void add(Car c);

	void update(int id, Car c);

	void delete(int id);

	Car get(int id);

	List<Car> getAll();
}
