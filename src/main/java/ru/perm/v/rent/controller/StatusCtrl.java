package ru.perm.v.rent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.SimpleDTO;
import ru.perm.v.rent.model.Status;

@RestController
@RequestMapping("/status")
@Api(tags = "Статусы автомобилей")
public class StatusCtrl extends ACtrl<Status, Long> {

	@ApiOperation(value = "Создать статус", response =
			Status.class)
	@PutMapping
	public Status create(@RequestBody SimpleDTO dto) {
		return service.getRepository()
				.save(new Status(dto.getName()));
	}

}
