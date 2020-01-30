package ru.perm.v.rent.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.service.AService;

public class ACtrl<ENTITY, KEY> {

	@Autowired
	protected AService<ENTITY, KEY> service;

	@ApiOperation(value = "Получение сущности по идентификатору")
	@GetMapping("/{id}")
	public ENTITY getById(@PathVariable KEY id) {
		return service.getRepository().getOne(id);
	}

	@ApiOperation(value = "Получение всех сущностей")
	@GetMapping
	public List<ENTITY> getAll() {
		return service.getRepository().findAll();
	}

	@ApiOperation(value = "Изменение сущности")
	@PostMapping
	public ENTITY update(@RequestBody ENTITY entity) {
		return service.getRepository().save(entity);
	}

	@ApiOperation(value = "Удаление сущности")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable KEY id) {
		service.getRepository().deleteById(id);
	}

}
