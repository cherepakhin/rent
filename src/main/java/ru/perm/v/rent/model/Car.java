package ru.perm.v.rent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Арендуемый автомобиль
 */
@ApiModel(description = "Описание автомобиля.")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {

	// Арендатор когда автомобиль не арендован
	public static final String NULL_RENTER = "-";

	@ApiModelProperty(notes = "Номер автомобиля")
	@Id
	private String label;

	@ApiModelProperty(notes = "Арендатор")
	@Column(columnDefinition = "varchar(255) default '-'")
	// TODO: Может быть выделить отдельную таблицу
	private String renter = "";

	@ApiModelProperty(notes = "Марка")
	@ManyToOne
	private ModelCar model;

	@ApiModelProperty(notes = "Пункт выдачи, где можно арендовать или последнее место выдачи")
	@ManyToOne
	private RentalPoint rentalPoint;

	@ApiModelProperty(notes = "Статус")
	@ManyToOne
	private Status status;

	public Car() {
	}

	public Car(String label) {
		this.label = label;
	}

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

