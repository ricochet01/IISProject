package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.CarManufacturer;
import hr.mperhoc.iisproject.model.list.CarManufacturersList;

public interface CarManufacturerRepository {

	void add(CarManufacturer cm);

	void update(int id, CarManufacturer cm);

	void delete(int id);

	CarManufacturer get(int id);

	CarManufacturersList getAll();
}
