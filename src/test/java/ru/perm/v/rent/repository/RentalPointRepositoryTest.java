package ru.perm.v.rent.repository;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.model.RentalPoint;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class RentalPointRepositoryTest {

	// Взято из import.sql
	private final static String RENTAL_POINT_NAME = "Пункт-1";
	private final static Long RENTAL_POINT_ID = 1L;

	@Autowired
	RentalPointRepository repository;

	@Test
	public void getByName() {
		RentalPoint rentalPoint = repository
				.getByName(RENTAL_POINT_NAME);
		assertEquals(RENTAL_POINT_ID, rentalPoint.getId());
	}
}