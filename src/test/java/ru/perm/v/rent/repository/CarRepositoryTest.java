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

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class CarRepositoryTest {

	private final static String LABEL = "111";
	@Autowired
	CarRepository repository;

	@Test
	@Sql("classpath:car.sql")
	public void getOne() {
		Car car = repository.getOne(LABEL);
		assertEquals(LABEL, car.getLabel());
	}

	@Test
	@Sql("classpath:car.sql")
	public void findAll() {
		List<Car> cars = repository.findAll();
		assertEquals(2, cars.size());
	}
}