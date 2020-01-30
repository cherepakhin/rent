package ru.perm.v.rent.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(description = "Строка отчета о средних цифр аренды.")
public class AvgReportDTO {

	@ApiModelProperty(notes = "Марка")
	private String modelCar = "";

	@ApiModelProperty(notes = "Среднее время аренды в часах")
	private BigDecimal val = new BigDecimal("0.00");

	public AvgReportDTO() {
	}

	public AvgReportDTO(String modelCar, BigDecimal val) {
		this.modelCar = modelCar;
		this.val = val;
	}

	public String getModelCar() {
		return modelCar;
	}

	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}

	public BigDecimal getVal() {
		return val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "AvgReportDTO{" +
				"modelCar='" + modelCar + '\'' +
				", val=" + val +
				'}';
	}
}
