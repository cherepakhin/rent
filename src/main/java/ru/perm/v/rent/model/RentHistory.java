package ru.perm.v.rent.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Сведения об аренде
 */
@ApiModel(description = "Сведения об аренде")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class RentHistory {

	@ApiModelProperty(notes = "Идентификатор")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(notes = "Арендованный автомобиль")
	@ManyToOne
	private Car car;

	@ApiModelProperty(notes = "Операция проведена в пункте")
	@ManyToOne
	private RentalPoint rentalPoint;

	@ApiModelProperty(notes = "Новый статус")
	@ManyToOne
	private Status newStatus;

	@ApiModelProperty(notes = "Время события")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime eventTime = LocalDateTime.now();

	@ApiModelProperty(notes = "Арендатор")
	@Column(columnDefinition = "varchar(255) default '-'")
	// TODO: Может быть выделить отдельную таблицу
	private String renter = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public RentalPoint getRentalPoint() {
		return rentalPoint;
	}

	public void setRentalPoint(RentalPoint rentalPoint) {
		this.rentalPoint = rentalPoint;
	}

	public Status getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(Status newStatus) {
		this.newStatus = newStatus;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getRenter() {
		return renter;
	}

	public void setRenter(String renter) {
		this.renter = renter;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RentHistory)) {
			return false;
		}

		RentHistory that = (RentHistory) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "RentHistory{" +
				"id=" + id +
				", car=" + car +
				", rentalPoint=" + rentalPoint +
				", newStatus=" + newStatus +
				", eventTime=" + eventTime +
				", renter='" + renter + '\'' +
				'}';
	}
}
