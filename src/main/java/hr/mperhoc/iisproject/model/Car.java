package hr.mperhoc.iisproject.model;

import java.io.Serializable;
import java.util.Objects;

import hr.mperhoc.iisproject.util.adapter.CarManufacturerAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car implements Serializable {
	private static final long serialVersionUID = 7503848412856392443L;
	private static int idCounter = 0;

	@XmlAttribute
	private final int id;
	@XmlJavaTypeAdapter(CarManufacturerAdapter.class)
	@XmlElement
	private CarManufacturer manufacturer;

	private String model;
	private int productionYear;
	private int power, torque, weight; // HP, Nm, Kg

	private int weightDistributionFront; // Percentage of the weight in the front of the car
	private int displacement; // Engine displacement in cc

	public Car() {
		this.id = idCounter++;
	}

	public Car(CarManufacturer manufacturer, String model, int year, int p, int t, int w, int wd, int d) {
		this();
		this.manufacturer = manufacturer;
		this.productionYear = year;
		this.power = p;
		this.torque = t;
		this.weight = w;
		this.weightDistributionFront = wd;
		this.displacement = d;
	}

	public int getId() {
		return id;
	}

	public CarManufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(CarManufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getTorque() {
		return torque;
	}

	public void setTorque(int torque) {
		this.torque = torque;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeightDistributionFront() {
		return weightDistributionFront;
	}

	public void setWeightDistributionFront(int weightDistributionFront) {
		this.weightDistributionFront = weightDistributionFront;
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, displacement, manufacturer, model, power, productionYear, torque, weight,
				weightDistributionFront);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Car other = (Car) obj;
		return displacement == other.displacement && Objects.equals(manufacturer, other.manufacturer)
				&& Objects.equals(model, other.model) && power == other.power && productionYear == other.productionYear
				&& torque == other.torque && weight == other.weight
				&& weightDistributionFront == other.weightDistributionFront && id == other.id;
	}

	@Override
	public String toString() {
		return "Car [manufacturer=" + manufacturer + ", model=" + model + ", productionYear=" + productionYear
				+ ", power=" + power + ", torque=" + torque + ", weight=" + weight + ", weightDistributionFront="
				+ weightDistributionFront + ", displacement=" + displacement + "]";
	}

}
