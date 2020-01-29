package ru.perm.v.rent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Марка автомобиля
 */
@Entity
public class ModelCar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public ModelCar() {
	}

	public ModelCar(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ModelCar)) {
			return false;
		}

		ModelCar modelCar = (ModelCar) o;

		return id != null ? id.equals(modelCar.id) : modelCar.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ModelCar{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
