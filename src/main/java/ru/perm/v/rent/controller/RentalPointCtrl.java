package ru.perm.v.rent.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.SimpleDTO;
import ru.perm.v.rent.model.RentalPoint;

@RestController
@RequestMapping("/rentalpoint")
public class RentalPointCtrl extends ACtrl<RentalPoint, Long> {

	@PutMapping
	public RentalPoint create(@RequestBody SimpleDTO dto) {
		return service.getRepository()
				.save(new RentalPoint(dto.getName()));
	}
}
