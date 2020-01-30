package ru.perm.v.rent.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.perm.v.rent.model.RentHistory;

@Repository
public interface RentHistoryRepository extends
		JpaRepository<RentHistory, Long> {

	@Override
	@Query("select distinct rh " +
			"from RentHistory rh " +
			"left join fetch rh.car " +
			"left join fetch rh.rentalPoint " +
			"left join fetch rh.newStatus " +
			"order by rh.eventTime"
	)
	List<RentHistory> findAll();

	@Query("select distinct rh " +
			"from RentHistory rh " +
			"left join fetch rh.car " +
			"left join fetch rh.rentalPoint " +
			"left join fetch rh.newStatus " +
			"where rh.eventTime between :startTime and :endTime " +
			"order by rh.eventTime"
	)
	List<RentHistory> findInPeriod(LocalDateTime startTime,
			LocalDateTime endTime);
}
