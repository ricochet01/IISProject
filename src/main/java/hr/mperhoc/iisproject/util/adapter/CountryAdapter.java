package hr.mperhoc.iisproject.util.adapter;

import hr.mperhoc.iisproject.model.Country;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CountryAdapter extends XmlAdapter<CountryAdapter.CountryDto, Country> {
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CountryDto {
		@XmlAttribute
		private int id;
		@XmlAttribute
		private String name;

		public CountryDto() {
		}

		public CountryDto(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	@Override
	public CountryDto marshal(Country value) throws Exception {
		return new CountryDto(value.getId(), value.getName());
	}

	@Override
	public Country unmarshal(CountryDto value) throws Exception {
		return new Country(value.id, value.name);
	}

}