package ru.perm.v.rent.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.perm.v.rent.service.AService;

public class ACtrl<ENTITY, KEY> {

	@Autowired
	protected AService<ENTITY, KEY> service;

	@GetMapping("/{id}")
	public ENTITY getById(@PathVariable KEY id) {
		return service.getRepository().getOne(id);
	}

	@GetMapping
	public List<ENTITY> getAll() {
		return service.getRepository().findAll();
	}

	@PostMapping
	public ENTITY update(@RequestBody ENTITY entity) {
		return service.getRepository().save(entity);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable KEY id) {
		service.getRepository().deleteById(id);
	}

}
