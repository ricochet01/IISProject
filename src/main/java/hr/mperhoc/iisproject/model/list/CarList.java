package hr.mperhoc.iisproject.model.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.mperhoc.iisproject.model.Car;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarList {
	@XmlElement(name = "car")
	private Map<Integer, Car> cars = new HashMap<>();

	public void add(Car c) {
		cars.put(c.getId(), c);
	}

	public Car get(int id) {
		return cars.get(id);
	}

	public void update(int id, Car c) {
		cars.put(id, c);
	}

	public void remove(int id) {
		cars.remove(id);
	}

	public int size() {
		return cars.size();
	}

	public List<Car> getCars() {
		return new ArrayList<>(cars.values());
	}
}
