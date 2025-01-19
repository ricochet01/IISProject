package hr.mperhoc.iisproject.model.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.mperhoc.iisproject.model.CarManufacturer;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manufacturers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarManufacturersList {
	@XmlElement(name = "manufacturer")
	private Map<Integer, CarManufacturer> manufacturers = new HashMap<>();

	public void add(CarManufacturer m) {
		manufacturers.put(m.getId(), m);
	}

	public CarManufacturer get(int id) {
		return manufacturers.get(id);
	}

	public void update(int id, CarManufacturer cm) {
		manufacturers.put(id, cm);
	}

	public void remove(int id) {
		manufacturers.remove(id);
	}

	public int size() {
		return manufacturers.size();
	}

	public List<CarManufacturer> getManufacturers() {
		return new ArrayList<>(manufacturers.values());
	}
}
