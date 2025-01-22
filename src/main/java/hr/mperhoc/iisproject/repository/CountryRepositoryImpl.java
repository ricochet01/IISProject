package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Country;
import hr.mperhoc.iisproject.model.list.CountryList;

public class CountryRepositoryImpl implements CountryRepository {
	private CountryList countries;

	public CountryRepositoryImpl() {
		countries = new CountryList();
	}

	@Override
	public void add(Country c) {
		countries.add(c);
	}

	@Override
	public void update(int id, Country c) {
		countries.update(id, c);
	}

	@Override
	public void delete(int id) {
		countries.remove(id);
	}

	@Override
	public Country get(int id) {
		return countries.get(id);
	}

	@Override
	public CountryList getAll() {
		return countries;
	}

}
