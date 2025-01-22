package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Manufacturer;
import hr.mperhoc.iisproject.model.list.ManufacturerList;

public interface ManufacturerRepository {

	void add(Manufacturer m);

	void update(int id, Manufacturer m);

	void delete(int id);

	Manufacturer get(int id);

	ManufacturerList getAll();
}
