package hr.mperhoc.iisproject.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manufacturer")
@XmlAccessorType(XmlAccessType.NONE)
public class CarManufacturer implements Serializable {
	private static final long serialVersionUID = 3687363956259598580L;
	private static int idCounter = 0;

	@XmlAttribute
	private final int id;
	@XmlAttribute
	private String name;

	public CarManufacturer() {
		this.id = idCounter++;
	}

	public CarManufacturer(String name) {
		this();
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarManufacturer other = (CarManufacturer) obj;
		return Objects.equals(name, other.name) && id == other.id;
	}

	@Override
	public String toString() {
		return "CarManufacturer [name=" + name + "]";
	}

}
