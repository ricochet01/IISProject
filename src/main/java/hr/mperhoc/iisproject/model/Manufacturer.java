package hr.mperhoc.iisproject.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manufacturer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Manufacturer implements Serializable {
	private static final long serialVersionUID = -4270950905685329692L;
	private static int idCounter = 0;

	@XmlAttribute
	private final int id;
	@XmlAttribute
	private String name;

	public Manufacturer() {
		this.id = idCounter++;
	}

	public Manufacturer(String name) {
		this();
		this.name = name;
	}

	public Manufacturer(int id, String name) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(name, other.name) && id == other.id;
	}

	@Override
	public String toString() {
		return "Manufacturer [name=" + name + "]";
	}
}
