package hr.mperhoc.iisproject.model.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.mperhoc.iisproject.model.Manufacturer;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manufacturers")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ManufacturerList implements Serializable {
	private static final long serialVersionUID = 1644183728576125364L;
	private Map<Integer, Manufacturer> manufacturers = new HashMap<>();

	public void add(Manufacturer m) {
		manufacturers.put(m.getId(), m);
	}

	public Manufacturer get(int id) {
		return manufacturers.get(id);
	}

	public void update(int id, Manufacturer cm) {
		manufacturers.put(id, cm);
	}

	public void remove(int id) {
		manufacturers.remove(id);
	}

	public int size() {
		return manufacturers.size();
	}

	@XmlElement(name = "manufacturer")
	public List<Manufacturer> getManufacturers() {
		return new ArrayList<>(manufacturers.values());
	}
}
