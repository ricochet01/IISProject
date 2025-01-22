package hr.mperhoc.iisproject.model;

import java.io.Serializable;
import java.util.Objects;

import hr.mperhoc.iisproject.util.adapter.CountryAdapter;
import hr.mperhoc.iisproject.util.adapter.ManufacturerAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "food")
@XmlAccessorType(XmlAccessType.FIELD)
public class Food implements Serializable {
	private static final long serialVersionUID = 7503848412856392443L;
	private static int idCounter = 0;

	@XmlAttribute
	private final int id;
	@XmlJavaTypeAdapter(ManufacturerAdapter.class)
	@XmlElement
	private Manufacturer manufacturer;

	private String name;
	private int amount;
	private String measuringUnit, ingredients;
	private int caloriesPer100;
	@XmlJavaTypeAdapter(CountryAdapter.class)
	@XmlElement
	private Country countryOfOrigin; // TODO: change to Country class

	public Food() {
		this.id = idCounter++;
	}

	public Food(Manufacturer manufacturer, String name, int amount, String measuringUnit, String ingredients,
			int caloriesPer100, Country countryOfOrigin) {
		this();
		this.manufacturer = manufacturer;
		this.name = name;
		this.amount = amount;
		this.measuringUnit = measuringUnit;
		this.ingredients = ingredients;
		this.caloriesPer100 = caloriesPer100;
		this.countryOfOrigin = countryOfOrigin;
	}

	public int getId() {
		return id;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public int getCaloriesPer100() {
		return caloriesPer100;
	}

	public void setCaloriesPer100(int caloriesPer100) {
		this.caloriesPer100 = caloriesPer100;
	}

	public Country getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(Country countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, caloriesPer100, countryOfOrigin, id, ingredients, manufacturer, measuringUnit,
				name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Food other = (Food) obj;
		return amount == other.amount && caloriesPer100 == other.caloriesPer100
				&& Objects.equals(countryOfOrigin, other.countryOfOrigin) && id == other.id
				&& Objects.equals(ingredients, other.ingredients) && Objects.equals(manufacturer, other.manufacturer)
				&& Objects.equals(measuringUnit, other.measuringUnit) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", manufacturer=" + manufacturer + ", name=" + name + ", amount=" + amount
				+ ", measuringUnit=" + measuringUnit + ", ingredients=" + ingredients + ", caloriesPer100="
				+ caloriesPer100 + ", countryOfOrigin=" + countryOfOrigin + "]";
	}

}
