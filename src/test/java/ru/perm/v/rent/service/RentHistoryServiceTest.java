package ru.perm.v.rent.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.dto.AvgReportDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class RentHistoryServiceTest {

	private static final Logger LOG =
			LoggerFactory.getLogger(RentHistoryServiceTest.class);

	@Autowired
	RentHistoryService service;

	@Test
	@Sql("classpath:renthistory.sql")
	public void getAvgReport() {
		List<AvgReportDTO> report = service.getAvgReport();
		assertEquals(1, report.size());
		assertEquals("-", report.get(0).getModelCar());
		assertEquals(0,
				report.get(0).getVal().compareTo(new BigDecimal("1.5")));
	}
}