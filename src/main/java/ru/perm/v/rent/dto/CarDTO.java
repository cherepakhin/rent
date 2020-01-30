package ru.perm.v.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO для ввода машины
 */
@ApiModel(description = "Описание автомобиля.")
public class CarDTO {

	@ApiModelProperty(notes = "Номер автомобиля")
	@JsonProperty(required = true)
	String label;

	@ApiModelProperty(notes = "Марка")
	@JsonProperty(required = true)
	String model;

	@ApiModelProperty(notes = "Пункт выдачи")
	@JsonProperty(required = true)
	String rentalPoint;

	@ApiModelProperty(notes = "Статус")
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
