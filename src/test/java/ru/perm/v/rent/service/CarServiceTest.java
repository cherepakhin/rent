package ru.perm.v.rent.service;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.dto.CarDTO;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.model.StatusConstants;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CarServiceTest {

	@Autowired
	CarService carService;

	@Test
	public void saveByDTO() {
		final String LABEL = "a241cc59";
		final String MODEL = "Toyota";
		final String RENTAL_POINT = "Пункт-1";

		CarDTO dto = new CarDTO(LABEL, MODEL, RENTAL_POINT, "");
		Car car = carService.saveByDTO(dto);

		assertEquals(MODEL, car.getModel().getName());
		assertEquals(LABEL, car.getLabel());
		assertEquals(RENTAL_POINT, car.getRentalPoint().getName());
		assertEquals(StatusConstants.FREE, car.getStatus().getId());
	}
}