package hr.mperhoc.iisproject.util.adapter;

import hr.mperhoc.iisproject.model.Country;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CountryAdapter extends XmlAdapter<String, Country> {

	@Override
	public Country unmarshal(String value) throws Exception {
		return new Country(value);
	}

	@Override
	public String marshal(Country value) throws Exception {
		return value.getName();
	}

}