package ru.perm.v.rent.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.junit.Before;
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
import ru.perm.v.rent.dto.SimpleDTO;
import ru.perm.v.rent.model.RentalPoint;
import ru.perm.v.rent.repository.RentalPointRepository;
import ru.perm.v.rent.service.RentalPointService;

@RunWith(SpringRunner.class)
@WebMvcTest(RentalPointCtrl.class)
@ActiveProfiles("test")
public class RentalPointCtrlTest {

	private final static String NAME = "NAME";
	private final static Long ID = 2L;
	private final static RentalPoint ENTITY = new RentalPoint(ID, NAME);
	private final static ObjectMapper objectMapper = new ObjectMapper();
	private static MediaType MEDIA_TYPE_JSON = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			StandardCharsets.UTF_8);

	@MockBean
	RentalPointService service;
	@MockBean
	RentalPointRepository repository;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		given(this.service.getRepository())
				.willReturn(repository);
	}

	@Test
	public void create() throws Exception {
		SimpleDTO dto = new SimpleDTO(NAME);
		String requestJson = objectMapper.writeValueAsString(dto);

		given(this.service.getRepository().save(new RentalPoint(NAME)))
				.willReturn(ENTITY);

		this.mockMvc
				.perform(put("/rentalpoint")
						.contentType(MEDIA_TYPE_JSON)
						.content(requestJson)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id").value(ID))
				.andExpect(jsonPath("$.name").value(NAME))
		;
	}
}