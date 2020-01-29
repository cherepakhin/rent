package ru.perm.v.rent.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.CarDTO;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.service.CarService;

@RestController
@RequestMapping("/car")
public class CarCtrl {

	@Autowired
	CarService service;

	@GetMapping("/{id}")
	public Car getById(@PathVariable String id) {
		return service.getRepository().getOne(id);
	}

	@GetMapping
	public List<Car> getAll() {
		return service.getRepository().findAll();
	}

	@PutMapping
	public Car create(@RequestBody CarDTO dto) {
		return service.saveByDTO(dto);
	}

	@PostMapping
	public Car update(@RequestBody CarDTO dto) {
		return service.saveByDTO(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		service.getRepository().deleteById(id);
	}
}
