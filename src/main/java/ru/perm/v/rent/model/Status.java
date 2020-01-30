package ru.perm.v.rent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Статус автомобиля (в аренде, свободен)
 */
@ApiModel(description = "Статус автомобиля (в аренде, свободен)")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Status {

	public static final Long FREE = 0L; // Можно арендовать
	public static final Long RENT = 1L; // Арендована

	@ApiModelProperty(notes = "Идентификатор")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(notes = "Название")
	private String name;

	public Status() {
	}

	public Status(String name) {
		this.name = name;
	}

	public Status(Long id, String name) {
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
		if (!(o instanceof Status)) {
			return false;
		}

		Status status = (Status) o;

		return id != null ? id.equals(status.id) : status.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Status{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
