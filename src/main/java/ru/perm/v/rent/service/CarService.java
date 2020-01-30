package ru.perm.v.rent.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.rent.dto.CarDTO;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.model.ModelCar;
import ru.perm.v.rent.model.RentalPoint;
import ru.perm.v.rent.model.Status;
import ru.perm.v.rent.repository.CarRepository;
import ru.perm.v.rent.repository.ModelCarRepository;
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
	 * @param rental - арендатор
	 * @return - арендованная машина
	 */
	//TODO: Реализовать аренду
	public Car take(String label, String rental) {
		return repository.getOne(label);
	}

	/**
	 * Сдать машину
	 *
	 * @param label           - номер машины
	 * @param nameRentalPoint - название пункта приема
	 * @return - машина
	 */
	//TODO: Сдать машину
	public Car returnCar(String label, String nameRentalPoint) {
		return repository.getOne(label);
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
