package hr.mperhoc.iisproject.repository;

import java.util.List;

import hr.mperhoc.iisproject.model.CarManufacturer;

public interface CarManufacturerRepository {

	void add(CarManufacturer cm);

	void update(int id, CarManufacturer cm);

	void delete(int id);

	CarManufacturer get(int id);

	List<CarManufacturer> getAll();
}
