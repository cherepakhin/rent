package ru.perm.v.rent.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Пункт проката
 */
@ApiModel(description = "Пункт проката")
@Entity
public class RentalPoint {

	@ApiModelProperty(notes = "Идентификатор")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ApiModelProperty(notes = "Название пункта проката")
	private String name;

	public RentalPoint() {
	}

	public RentalPoint(String name) {
		this.name = name;
	}

	public RentalPoint(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RentalPoint)) {
			return false;
		}

		RentalPoint that = (RentalPoint) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "RentalPoint{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
