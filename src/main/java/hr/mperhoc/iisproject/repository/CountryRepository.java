package hr.mperhoc.iisproject.repository;

import hr.mperhoc.iisproject.model.Country;
import hr.mperhoc.iisproject.model.list.CountryList;

public interface CountryRepository {

	void add(Country c);

	void update(int id, Country c);

	void delete(int id);

	Country get(int id);

	CountryList getAll();

}
