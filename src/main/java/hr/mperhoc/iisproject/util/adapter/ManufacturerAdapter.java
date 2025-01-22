package hr.mperhoc.iisproject.util.adapter;

import hr.mperhoc.iisproject.model.Manufacturer;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ManufacturerAdapter extends XmlAdapter<ManufacturerAdapter.ManufacturerDto, Manufacturer> {
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ManufacturerDto {
		@XmlAttribute
		private int id;
		@XmlAttribute
		private String name;

		public ManufacturerDto() {
		}

		public ManufacturerDto(int id, String name) {
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
	public ManufacturerDto marshal(Manufacturer value) throws Exception {
		return new ManufacturerDto(value.getId(), value.getName());
	}

	@Override
	public Manufacturer unmarshal(ManufacturerDto value) throws Exception {
		return new Manufacturer(value.id, value.name);
	}

}
