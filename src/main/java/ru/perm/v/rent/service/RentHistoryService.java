package ru.perm.v.rent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.perm.v.rent.model.RentHistory;

@Service
public class RentHistoryService extends AService<RentHistory, Long> {

	private static final Logger LOG =
			LoggerFactory.getLogger(RentHistoryService.class);

}
