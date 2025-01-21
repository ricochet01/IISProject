package hr.mperhoc.iisproject.model.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.mperhoc.iisproject.model.Food;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "foods")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FoodList {
	private Map<Integer, Food> foods = new HashMap<>();

	public void add(Food f) {
		foods.put(f.getId(), f);
	}

	public Food get(int id) {
		return foods.get(id);
	}

	public void update(int id, Food f) {
		foods.put(id, f);
	}

	public void remove(int id) {
		foods.remove(id);
	}

	public int size() {
		return foods.size();
	}

	@XmlElement(name = "food")
	public List<Food> getFoods() {
		return new ArrayList<>(foods.values());
	}
}
