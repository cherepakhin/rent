package ru.perm.v.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.rent.model.RentHistory;

@Repository
public interface RentHistoryRepository extends
		JpaRepository<RentHistory, Long> {

}