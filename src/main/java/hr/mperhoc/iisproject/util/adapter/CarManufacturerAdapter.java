package hr.mperhoc.iisproject.util.adapter;

import hr.mperhoc.iisproject.model.CarManufacturer;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CarManufacturerAdapter extends XmlAdapter<String, CarManufacturer> {

	@Override
	public CarManufacturer unmarshal(String value) throws Exception {
		return new CarManufacturer(value);
	}

	@Override
	public String marshal(CarManufacturer value) throws Exception {
		return value.getName();
	}

}
