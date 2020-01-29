package ru.perm.v.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.SimpleDTO;
import ru.perm.v.rent.dto.Statuses;
import ru.perm.v.rent.model.Status;
import ru.perm.v.rent.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusCtrl {

	@Autowired
	StatusService statusService;

	@GetMapping("/{id}")
	public Status getById(@PathVariable Long id) {
		return statusService.getRepository().findById(id).orElse(new Status());
	}

	@GetMapping
	public Statuses getAll() {
		return new Statuses(statusService.getRepository().findAll(Sort.by(
				"name")));
	}

	@PutMapping
	public Status create(@RequestBody SimpleDTO dto) {
		return statusService.getRepository()
				.save(new Status(dto.getName()));
	}

	@PostMapping
	public Status update(@RequestBody Status status) {
		return statusService.getRepository().save(status);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		statusService.getRepository().deleteById(id);
	}
}
