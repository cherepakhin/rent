package ru.perm.v.rent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.rent.dto.AvgReportDTO;
import ru.perm.v.rent.model.RentHistory;
import ru.perm.v.rent.service.RentHistoryService;

@RestController
@RequestMapping("/renthistory")
@Api(tags = "История аренды")
public class RentHistoryCtrl extends ACtrl<RentHistory, Long> {

	@Autowired
	RentHistoryService rentHistoryService;

	@ApiOperation(value = "Получение записей аренды", response =
			List.class)
	@GetMapping("/period")
	public List<RentHistory> findInPeriod(
			@ApiParam(value = "Начало периода. Формат "
					+ "\"yyyy-MM-dd HH:mm:ss\"", required = true)
			@RequestParam LocalDateTime start,

			@ApiParam(value = "Конец периода. Формат "
					+ "\"yyyy-MM-dd HH:mm:ss\"\"", required = true)
			@RequestParam LocalDateTime end) {
		return rentHistoryService.findInPeriod(start, end);
	}

	@ApiOperation(value = "Отчет со средними цифрами аренды", response =
			List.class)
	@GetMapping("/avgreport")
	public List<AvgReportDTO> getAvgReport() {
		return rentHistoryService.getAvgReport();
	}


}
