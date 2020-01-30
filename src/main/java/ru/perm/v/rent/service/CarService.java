package ru.perm.v.rent.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.rent.dto.CarDTO;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.model.ModelCar;
import ru.perm.v.rent.model.RentHistory;
import ru.perm.v.rent.model.RentalPoint;
import ru.perm.v.rent.model.Status;
import ru.perm.v.rent.repository.CarRepository;
import ru.perm.v.rent.repository.ModelCarRepository;
import ru.perm.v.rent.repository.RentHistoryRepository;
import ru.perm.v.rent.repository.RentalPointRepository;
import ru.perm.v.rent.repository.StatusRepository;

@Service
public class CarService extends AService<Car, String> {

	private static final Logger LOG =
			LoggerFactory.getLogger(CarService.class);

	@Autowired
	ModelCarRepository modelCarRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	RentalPointRepository rentalPointRepository;

	@Autowired
	RentHistoryRepository rentHistoryRepository;

	@Autowired
	CarRepository repository;

	public Car saveByDTO(CarDTO dto) {
		Status status =
				(dto.getStatus().isEmpty() || dto.getStatus().isBlank()) ?
						statusRepository
								.getOne(Status.FREE) :
						statusRepository.getByName(dto.getStatus());
		RentalPoint rentalPoint = rentalPointRepository
				.getByName(dto.getRentalPoint());
		LOG.info(String.format("DTO rentalPoint: %s", dto.getRentalPoint()));
		LOG.info(String.format("rentalPoint: %s", rentalPoint));
		ModelCar modelCar = modelCarRepository
				.getByName(dto.getModel());
		Car car = new Car();
		car.setLabel(dto.getLabel());
		car.setModel(modelCar);
		car.setStatus(status);
		car.setRentalPoint(rentalPoint);
		return repository.save(car);
	}

	/**
	 * Взять машину в прокат
	 *
	 * @param label  - номер машины
	 * @param renter - арендатор
	 * @return - арендованная машина
	 */
	public Car take(String label, String renter) {
		// Смена статуса автомобиля
		Car car = repository.getOne(label);
		Status rentStatus = statusRepository.getOne(Status.RENT);
		car.setRenter(renter);
		car.setStatus(rentStatus);
		Car changedCar = repository.save(car);

		// Регистрация аренды
		RentHistory record = new RentHistory();
		record.setCar(changedCar);
		record.setRenter(renter);
		record.setNewStatus(rentStatus);
		record.setRentalPoint(car.getRentalPoint());
		rentHistoryRepository.save(record);
		return changedCar;
	}

	/**
	 * Сдать машину
	 *
	 * @param label           - номер машины
	 * @param nameRentalPoint - название пункта приема
	 * @return - машина
	 */
	public Car returnCar(String label, String nameRentalPoint) {
		Status freeStatus = statusRepository.getOne(Status.FREE);
		RentalPoint rentalPoint = rentalPointRepository
				.getByName(nameRentalPoint);

		Car car = repository.getOne(label);

		// Регистрация сдачи автомобиля
		RentHistory record = new RentHistory();
		record.setCar(car);
		record.setRenter(car.getRenter());
		record.setNewStatus(freeStatus);
		record.setRentalPoint(rentalPoint);
		rentHistoryRepository.save(record);

		// Смена статуса автомобиля
		car.setRenter(Car.NULL_RENTER);
		car.setStatus(freeStatus);
		car.setRentalPoint(rentalPoint);
		return repository.save(car);
	}

	/**
	 * Получить свободные для аренды машины
	 *
	 * @param nameRentalPoint - пункт выдачи (если пустая строка, то для всех
	 *                        пунктов)
	 * @return - список свободных машин
	 */
	public List<Car> getFreeForRent(String nameRentalPoint) {
		Status freeStatus = statusRepository.getOne(Status.FREE);
		return nameRentalPoint.isBlank() ?
				repository.findByStatusName(freeStatus.getName()) :
				repository.findByStatusNameAndRentalPointName(
						freeStatus.getName(), nameRentalPoint);
	}
}
