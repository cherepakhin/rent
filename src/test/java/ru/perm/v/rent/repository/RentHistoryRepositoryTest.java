package ru.perm.v.rent.repository;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.model.RentHistory;
import ru.perm.v.rent.service.CarService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class RentHistoryRepositoryTest {

	private final static String LABEL = "333";
	private final static String RENTER = "RENTER";
	private final static String RENTAL_POINT = "Пункт-1";

	@Autowired
	RentHistoryRepository repository;

	@Autowired
	CarService carService;

	@Test
	public void findAll() {
		carService.take(LABEL, RENTER);
		carService.returnCar(LABEL, RENTAL_POINT);
		List<RentHistory> records = repository.findAll();
		assertEquals(2, records.size());
	}

	@Test
	public void findInPeriod() {
		carService.take(LABEL, RENTER);
		carService.returnCar(LABEL, RENTAL_POINT);
		LocalDateTime startTime = LocalDateTime.now().minusSeconds(1);
		LocalDateTime endTime = LocalDateTime.now().plusSeconds(1);
		List<RentHistory> records = repository.findInPeriod(startTime, endTime);
		assertEquals(2, records.size());
	}

	@Test
	public void findNotInPeriod() {
		carService.take(LABEL, RENTER);
		carService.returnCar(LABEL, RENTAL_POINT);

		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now();
		List<RentHistory> records = repository
				.findInPeriod(startTime.minusSeconds(1),
						endTime.minusSeconds(1));
		assertEquals(0, records.size());

		records = repository.findInPeriod(startTime.plusSeconds(1),
				endTime.plusSeconds(1));
		assertEquals(0, records.size());
	}

}