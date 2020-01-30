package ru.perm.v.rent.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.model.Status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class CarRepositoryTest {

	private final static String LABEL = "111";
	private final static String STATUS_NAME = "Свободен";

	@Autowired
	CarRepository repository;

	@Test
	public void getOne() {
		Car car = repository.getOne(LABEL);
		assertEquals(LABEL, car.getLabel());
	}

	@Test
	public void findAll() {
		List<Car> cars = repository.findAll();
		assertEquals(3, cars.size());
	}

	@Test
	public void findByStatusName() {
		List<Car> cars = repository.findByStatusName(STATUS_NAME);
		assertEquals(2, cars.size());
		assertEquals(Status.FREE, cars.get(0).getStatus().getId());
		assertEquals(STATUS_NAME, cars.get(0).getStatus().getName());
	}

	@Test
	public void findByStatusNameAndRentalPointName() {
		final String RENTAL_POINT_NAME = "-";
		List<Car> cars =
				repository.findByStatusNameAndRentalPointName(STATUS_NAME,
						RENTAL_POINT_NAME);
		assertEquals(1, cars.size());
		assertEquals(Status.FREE, cars.get(0).getStatus().getId());
		assertEquals(RENTAL_POINT_NAME, cars.get(0).getRentalPoint().getName());
	}
}