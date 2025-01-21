package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Manufacturer;
import hr.mperhoc.iisproject.model.list.ManufacturersList;

public class ManufacturerRepositoryImpl implements ManufacturerRepository {
	private ManufacturersList manufacturers;

	public ManufacturerRepositoryImpl() {
		manufacturers = new ManufacturersList();
	}

	@Override
	public void add(Manufacturer m) {
		manufacturers.add(m);
	}

	@Override
	public void update(int id, Manufacturer m) {
		manufacturers.update(id, m);
	}

	@Override
	public void delete(int id) {
		manufacturers.remove(id);
	}

	@Override
	public Manufacturer get(int id) {
		return manufacturers.get(id);
	}

	@Override
	public ManufacturersList getAll() {
		return manufacturers;
	}
}
