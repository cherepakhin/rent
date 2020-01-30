package ru.perm.v.rent.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
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
public class RentHistory {

	@ApiModelProperty(notes = "Идентификатор")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(notes = "Арендованный автомобиль")
	@ManyToOne
	private Car car;

	@ApiModelProperty(notes = "Взят из пункта")
	@ManyToOne
	private RentalPoint srcPoint;

	@ApiModelProperty(notes = "Время выдачи машины")
	private LocalDateTime startTime = LocalDateTime.now();

	// Может быть null. Означает что машина в аренде
	@ApiModelProperty(notes = "Принят в пукте")
	@ManyToOne
	private RentalPoint dstPoint;

	// Может быть null. Означает что машина в аренде
	@ApiModelProperty(notes = "Время принятия машины от арендатора")
	private LocalDateTime endTime;

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

	public RentalPoint getSrcPoint() {
		return srcPoint;
	}

	public void setSrcPoint(RentalPoint srcPoint) {
		this.srcPoint = srcPoint;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public RentalPoint getDstPoint() {
		return dstPoint;
	}

	public void setDstPoint(RentalPoint dstPoint) {
		this.dstPoint = dstPoint;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
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
				", srcPoint=" + srcPoint +
//				", startTime=" + startTime +
//				", dstPoint=" + dstPoint +
//				", endTime=" + endTime +
				'}';
	}
}
