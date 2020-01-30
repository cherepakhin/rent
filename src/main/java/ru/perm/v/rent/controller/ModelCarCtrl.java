package ru.perm.v.rent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.SimpleDTO;
import ru.perm.v.rent.model.ModelCar;

@RestController
@RequestMapping("/modelcar")
@Api(tags = "Марки автомобилей")
public class ModelCarCtrl extends ACtrl<ModelCar, Long> {

	@ApiOperation(value = "Создать описание марки", response =
			ModelCar.class)
	@PutMapping
	public ModelCar create(@RequestBody SimpleDTO dto) {
		return service.getRepository()
				.save(new ModelCar(dto.getName()));
	}

}
