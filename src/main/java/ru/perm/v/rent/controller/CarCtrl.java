package ru.perm.v.rent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.CarDTO;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.service.CarService;

@RestController
@RequestMapping("/car")
@Api(tags = "Управление автомобилями")
public class CarCtrl {

	@Autowired
	CarService service;

	@ApiOperation(value = "Получение описания автомобиля по номеру", response =
			Car.class)
	@GetMapping("/{id}")
	public Car getById(
			@ApiParam(value = "Номер автомобиля", required = true)
			@PathVariable String id) {
		return service.getRepository().getOne(id);
	}

	@ApiOperation(value = "Получение всех автомобилей", response = List.class)
	@GetMapping
	public List<Car> getAll() {
		return service.getRepository().findAll();
	}

	/**
	 * Взять машину в прокат
	 *
	 * @param label  - номер машины
	 * @param renter - арендатор
	 * @return - арендованная машина
	 */
	@ApiOperation(value = "Взять машину в прокат", response = Car.class)
	@GetMapping("/take")
	public Car take(
			@ApiParam(value = "Номер автомобиля", required = true)
			@RequestParam(name = "label") String label,

			@ApiParam(value = "Пункт выдачи", required = true)
			@RequestParam(name = "renter") String renter) {
		return service.take(label, renter);
	}

	/**
	 * Сдать машину
	 *
	 * @param label           - номер машины
	 * @param nameRentalPoint - название пункта приема
	 * @return - машина
	 */
	@ApiOperation(value = "Вернуть машину в пункт выдачи", response = Car.class)
	@GetMapping("/return")
	public Car returnCar(
			@ApiParam(value = "Номер автомобиля", required = true)
			@RequestParam(name = "label") String label,

			@ApiParam(value = "Пункт приема", required = true)
			@RequestParam(name = "point") String nameRentalPoint) {
		return service.returnCar(label, nameRentalPoint);
	}

	/**
	 * Получить свободные для аренды машины
	 *
	 * @param point - пункт выдачи (если пустая строка, то для всех * пунктов)
	 * @return - список свободных машин
	 */
	@ApiOperation(value = "Получить свободные для аренды машины", response =
			List.class)
	@GetMapping("/free")
	public List<Car> getFreeForRent(
			@ApiParam(value = "Пункт выдачи")
			@RequestParam(defaultValue = "") String point) {
		return service.getFreeForRent(point);
	}


	@ApiOperation(value = "Создать описание новой машины", response =
			Car.class)
	@PutMapping
	public Car create(
			@ApiParam(value = "Описание", required = true)
			@RequestBody CarDTO dto) {
		return service.saveByDTO(dto);
	}

	@ApiOperation(value = "Изменить описание машины", response =
			Car.class)
	@PostMapping
	public Car update(
			@ApiParam(value = "Описание", required = true)
			@RequestBody CarDTO dto) {
		return service.saveByDTO(dto);
	}

	@ApiOperation(value = "Удаление описания машины")
	@DeleteMapping("/{id}")
	public void delete(
			@ApiParam(value = "Номер автомобиля", required = true)
			@PathVariable String id) {
		service.getRepository().deleteById(id);
	}


}
