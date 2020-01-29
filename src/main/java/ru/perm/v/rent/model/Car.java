package ru.perm.v.rent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Арендуемый автомобиль
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {

	//Номер автомобиля
	@Id
	private String label;

	// Арендатор
	// TODO: Может быть выделить отдельную таблицу
	private String renter = "";

	// Марка
	@NotNull
	@ManyToOne
	private ModelCar model;

	// Пункт выдачи, где можно арендовать или последнее место выдачи
	@NotNull
	@ManyToOne
	private RentalPoint rentalPoint;

	// Статус
	@NotNull
	@ManyToOne
	private Status status;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRenter() {
		return renter;
	}

	public void setRenter(String renter) {
		this.renter = renter;
	}

	public ModelCar getModel() {
		return model;
	}

	public void setModel(ModelCar model) {
		this.model = model;
	}

	public RentalPoint getRentalPoint() {
		return rentalPoint;
	}

	public void setRentalPoint(RentalPoint rentalPoint) {
		this.rentalPoint = rentalPoint;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Car)) {
			return false;
		}

		Car car = (Car) o;

		return label != null ? label.equals(car.label) : car.label == null;
	}

	@Override
	public int hashCode() {
		return label != null ? label.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Car{" +
				"label='" + label + '\'' +
				", renter='" + renter + '\'' +
				", model=" + model +
				", rentalPoint=" + rentalPoint +
				", status=" + status +
				'}';
	}
}

