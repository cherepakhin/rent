package ru.perm.v.rent.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Арендуемый автомобиль
 */
@Entity
public class Car {

	//Номер автомобиля
	@Id
	private String label;

	// Арендатор
	// TODO: Может быть выделить отдельную таблицу
	private String renter;

	// Марка
	@ManyToOne
	private ModelCar model;

	// Пункт выдачи, где можно арендовать или последнее место выдачи
	@ManyToOne
	private RentalPoint rentalPoint;

	// Статус
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
}
