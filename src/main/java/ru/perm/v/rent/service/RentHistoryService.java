package ru.perm.v.rent.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.rent.dto.AvgReportDTO;
import ru.perm.v.rent.model.RentHistory;
import ru.perm.v.rent.repository.RentHistoryRepository;

@Service
public class RentHistoryService extends AService<RentHistory, Long> {

	@Autowired
	RentHistoryRepository rentHistoryRepository;

	@Autowired
	EntityManager entityManager;

	public List<RentHistory> findInPeriod(LocalDateTime startTime,
			LocalDateTime endTime) {
		return rentHistoryRepository.findInPeriod(startTime, endTime);
	}

	/**
	 * Получение отчета со средними значениями аренды вида:
	 *
	 * @return - строки отчета
	 */
	public List<AvgReportDTO> getAvgReport() {
		Query q = entityManager.createNativeQuery(""
				+ "select model_car.name as modelCar, (st0.summa-st1.summa)/st1.cnt/60 as val "
				+ "from "
				+ "(select car_label, count(*) as cnt, sum(datediff('s', '2020-01-28', event_time)/60) as summa "
				+ "from rent_history, car,model_car "
				+ "where new_status_id=1 "
				+ "and rent_history.car_label=car.label "
				+ "and car.model_id=model_car.id "
				+ "group by model_car.name) st1, "
				+ "(select car_label, count(*) as cnt, sum(datediff('s', '2020-01-28', event_time)/60) as summa "
				+ "from rent_history, car,model_car "
				+ "where new_status_id=0 "
				+ "and rent_history.car_label=car.label "
				+ "and car.model_id=model_car.id "
				+ "group by car_label) st0,"
				+ "car,"
				+ "model_car "
				+ "where "
				+ "st1.car_label=st0.car_label and "
				+ "st1.car_label=car.label and "
				+ "car.model_id=model_car.id");
		List<Object[]> records  = q.getResultList();
		ArrayList<AvgReportDTO> ret = new ArrayList<>();

		records.forEach(objects -> ret.add(new AvgReportDTO(
				objects[0].toString(),
				(BigDecimal) objects[1]))
		);
		return ret;
	}
}
