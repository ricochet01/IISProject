package hr.mperhoc.iisproject.repository;

import java.util.List;

import hr.mperhoc.iisproject.model.CarManufacturer;
import hr.mperhoc.iisproject.model.list.CarManufacturersList;

public class CarManufacturerRepositoryImpl implements CarManufacturerRepository {
	private CarManufacturersList manufacturers;

	public CarManufacturerRepositoryImpl() {
		manufacturers = new CarManufacturersList();
	}

	@Override
	public void add(CarManufacturer cm) {
		manufacturers.add(cm);
	}

	@Override
	public void update(int id, CarManufacturer cm) {
		manufacturers.update(id, cm);
	}

	@Override
	public void delete(int id) {
		manufacturers.remove(id);
	}

	@Override
	public CarManufacturer get(int id) {
		return manufacturers.get(id);
	}

	@Override
	public List<CarManufacturer> getAll() {
		return manufacturers.getManufacturers();
	}
}
