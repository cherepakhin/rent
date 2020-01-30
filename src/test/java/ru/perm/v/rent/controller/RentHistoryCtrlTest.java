package ru.perm.v.rent.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.perm.v.rent.model.RentHistory;
import ru.perm.v.rent.service.RentHistoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(RentHistoryCtrl.class)
@ActiveProfiles("test")
public class RentHistoryCtrlTest {

	private final static ObjectMapper objectMapper = new ObjectMapper();
	private static final MediaType MEDIA_TYPE_JSON = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			StandardCharsets.UTF_8);

	@MockBean
	RentHistoryService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findInPeriod() throws Exception {
		List<RentHistory> records = new ArrayList<>();
		records.add(new RentHistory());
		records.add(new RentHistory());
		given(this.service.findInPeriod(any(), any()))
				.willReturn(records);

		this.mockMvc
				.perform(get("/renthistory/period")
						.param("start", "2000-01-01 01:01:01")
						.param("end", "2200-01-01 01:01:01")
						.contentType(MEDIA_TYPE_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(2)))
		;
	}
}