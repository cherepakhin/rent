package ru.perm.v.rent.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.rent.model.RentHistory;
import ru.perm.v.rent.repository.RentHistoryRepository;

@Service
public class RentHistoryService extends AService<RentHistory, Long> {

	@Autowired
	RentHistoryRepository repository;

	public List<RentHistory> findInPeriod(LocalDateTime startTime,
			LocalDateTime endTime) {
		return repository.findInPeriod(startTime, endTime);
	}

}
