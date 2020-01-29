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
import ru.perm.v.rent.model.ModelCar;
import ru.perm.v.rent.repository.ModelCarRepository;
import ru.perm.v.rent.service.ModelCarService;

@RunWith(SpringRunner.class)
@WebMvcTest(ModelCarCtrl.class)
@ActiveProfiles("test")
public class ModelCarCtrlTest {

	private final static String NAME = "NAME";
	private final static Long ID = 2L;
	private final static ModelCar ENTITY = new ModelCar(ID, NAME);
	private final static ObjectMapper objectMapper = new ObjectMapper();
	private static MediaType MEDIA_TYPE_JSON = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			StandardCharsets.UTF_8);

	@MockBean
	ModelCarService service;
	@MockBean
	ModelCarRepository repository;
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

		given(this.service.getRepository().save(new ModelCar(NAME)))
				.willReturn(ENTITY);

		this.mockMvc
				.perform(put("/modelcar")
						.contentType(MEDIA_TYPE_JSON)
						.content(requestJson)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id").value(ID))
				.andExpect(jsonPath("$.name").value(NAME))
		;
	}
}