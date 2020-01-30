package ru.perm.v.rent.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.SimpleDTO;
import ru.perm.v.rent.model.Status;

//TODO: Добавить Swagger
@RestController
@RequestMapping("/status")
public class StatusCtrl extends ACtrl<Status, Long> {

	@PutMapping
	public Status create(@RequestBody SimpleDTO dto) {
		return service.getRepository()
				.save(new Status(dto.getName()));
	}

}
