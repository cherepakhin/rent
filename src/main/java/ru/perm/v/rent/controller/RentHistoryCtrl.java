package ru.perm.v.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.service.RentHistoryService;

//TODO: Добавить Swagger
//TODO: Сделать реализацию
@RestController
@RequestMapping("/renthistory")
public class RentHistoryCtrl {

	@Autowired
	RentHistoryService service;

}
