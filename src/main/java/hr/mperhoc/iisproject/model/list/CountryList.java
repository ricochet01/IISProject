package hr.mperhoc.iisproject.model.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.mperhoc.iisproject.model.Country;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "countries")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CountryList {
	private Map<Integer, Country> countries = new HashMap<>();

	public void add(Country c) {
		countries.put(c.getId(), c);
	}

	public Country get(int id) {
		return countries.get(id);
	}

	public void update(int id, Country c) {
		countries.put(id, c);
	}

	public void remove(int id) {
		countries.remove(id);
	}

	public int size() {
		return countries.size();
	}

	@XmlElement(name = "country")
	public List<Country> getCountries() {
		return new ArrayList<>(countries.values());
	}
}
