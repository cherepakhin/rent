package ru.perm.v.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.CarDTO;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.service.CarService;

@RestController
@RequestMapping("/car")
public class CarCtrl extends ACtrl<Car, String> {

	@Autowired
	CarService service;

	@PutMapping
	public Car create(@RequestBody CarDTO dto) {
		return service.saveByDTO(dto);
	}

}
