package ru.perm.v.rent.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Сведения об аренде
 */
@Entity
public class RentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Арендованный автомобиль
	@ManyToOne
	private Car car;

	// взят из пункта
	@ManyToOne
	private RentalPoint srcPoint;

	// Время выдачи машины
	private LocalDateTime startTime = LocalDateTime.now();

	// Принят в пукте
	// Может быть null. Означает что машина в аренде
	@ManyToOne
	private RentalPoint dstPoint;

	// Время принятия машины от арендатора
	// Может быть null. Означает что машина в аренде
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
