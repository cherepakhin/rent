package ru.perm.v.rent.service;

import static junit.framework.TestCase.assertEquals;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.dto.CarDTO;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.model.RentHistory;
import ru.perm.v.rent.model.Status;
import ru.perm.v.rent.repository.RentHistoryRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class CarServiceTest {

	@Autowired
	CarService carService;

	@Autowired
	RentHistoryRepository rentHistoryRepository;

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
		assertEquals(Status.FREE, car.getStatus().getId());
	}

	@Test
	public void saveExistByDTO() {
		final String LABEL = "111";
		final String MODEL = "Toyota";
		final String RENTAL_POINT = "Пункт-1";

		CarDTO dto = new CarDTO(LABEL, MODEL, RENTAL_POINT, "");
		Car car = carService.saveByDTO(dto);

		assertEquals(MODEL, car.getModel().getName());
		assertEquals(LABEL, car.getLabel());
		assertEquals(RENTAL_POINT, car.getRentalPoint().getName());
		assertEquals(Status.FREE, car.getStatus().getId());
	}

	@Test
	public void take() {
		final String LABEL = "111";
		final String RENTER = "RENTER";

		Car car = carService.take(LABEL, RENTER);

		assertEquals(LABEL, car.getLabel());
		assertEquals(RENTER, car.getRenter());
		assertEquals(Status.RENT, car.getStatus().getId());
		List<RentHistory> listRentHistory = rentHistoryRepository
				.findAll();
		assertEquals(1, listRentHistory.size());
		assertEquals(LABEL, listRentHistory.get(0).getCar().getLabel());
		assertEquals(RENTER, listRentHistory.get(0).getRenter());
		assertEquals(car.getRentalPoint(),
				listRentHistory.get(0).getRentalPoint());
		assertEquals(Status.RENT,
				listRentHistory.get(0).getNewStatus().getId());
	}

	@Test
	public void returnCar() {
		final String LABEL = "222";
		final String RENTAL_POINT = "Пункт-0";

		Car car = carService.returnCar(LABEL, RENTAL_POINT);

		assertEquals(LABEL, car.getLabel());
		assertEquals(Car.NULL_RENTER, car.getRenter());
		assertEquals(Status.FREE, car.getStatus().getId());

		List<RentHistory> listRentHistory = rentHistoryRepository
				.findAll();
		assertEquals(1, listRentHistory.size());
		assertEquals(LABEL, listRentHistory.get(0).getCar().getLabel());
		assertEquals(car.getRenter(), listRentHistory.get(0).getRenter());
		assertEquals(car.getRentalPoint(),
				listRentHistory.get(0).getRentalPoint());
		assertEquals(Status.FREE,
				listRentHistory.get(0).getNewStatus().getId());
	}
}