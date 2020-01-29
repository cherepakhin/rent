package ru.perm.v.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO для ввода машины
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDTO {

	// Номер машины
	@JsonProperty(required = true)
	String label;
	// Марка
	@JsonProperty(required = true)
	String model;
	// Пункт выдачи
	@JsonProperty(required = true)
	String rentalPoint;
	// Статус
	@JsonProperty(required = false)
	String status = "";

	public CarDTO() {
	}

	public CarDTO(String label, String model, String rentalPoint,
			String status) {
		this.label = label;
		this.model = model;
		this.rentalPoint = rentalPoint;
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRentalPoint() {
		return rentalPoint;
	}

	public void setRentalPoint(String rentalPoint) {
		this.rentalPoint = rentalPoint;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CarDTO{" +
				"label='" + label + '\'' +
				", model='" + model + '\'' +
				", rentalPoint='" + rentalPoint + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
