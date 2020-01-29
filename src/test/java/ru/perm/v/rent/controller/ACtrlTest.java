package ru.perm.v.rent.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
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
import ru.perm.v.rent.model.Status;
import ru.perm.v.rent.repository.StatusRepository;
import ru.perm.v.rent.service.StatusService;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusCtrl.class)
@ActiveProfiles("test")
public class ACtrlTest {

	private final static String NAME = "NAME";
	private final static Long ID = 2L;
	private final static Status STATUS_TEST = new Status(ID, NAME);
	private final static ObjectMapper objectMapper = new ObjectMapper();
	private static MediaType MEDIA_TYPE_JSON =new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			StandardCharsets.UTF_8);
	@MockBean
	StatusService statusService;
	@MockBean
	StatusRepository statusRepository;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		given(this.statusService.getRepository())
				.willReturn(statusRepository);
	}

	@Test
	public void getAll() throws Exception {
		ArrayList<Status> statuses = new ArrayList<>();
		statuses.add(STATUS_TEST);
		given(this.statusService.getRepository().findAll())
				.willReturn(statuses);

		this.mockMvc.perform(get("/status"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(NAME)))
				.andExpect(jsonPath("$[0].id", is(ID.intValue())))
				.andReturn();
	}

	@Test
	public void create() throws Exception {
		SimpleDTO dto = new SimpleDTO(NAME);
		String requestJson = objectMapper.writeValueAsString(dto);

		given(this.statusService.getRepository().save(new Status(NAME)))
				.willReturn(STATUS_TEST);

		this.mockMvc
				.perform(put("/status")
						.contentType(MEDIA_TYPE_JSON)
						.content(requestJson)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id").value(ID))
				.andExpect(jsonPath("$.name").value(NAME))
		;
	}

	@Test
	public void update() throws Exception {
		String requestJson = objectMapper.writeValueAsString(STATUS_TEST);

		given(this.statusService.getRepository().save(STATUS_TEST))
				.willReturn(STATUS_TEST);

		this.mockMvc
				.perform(post("/status")
						.contentType(MEDIA_TYPE_JSON)
						.content(requestJson)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.name").value(NAME))
		;
	}

	@Test
	public void getById() throws Exception {
		given(this.statusService.getRepository().getOne(ID))
				.willReturn(STATUS_TEST);

		this.mockMvc
				.perform(get(String.format("/status/%d",ID)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id").value(ID))
				.andExpect(jsonPath("$.name").value(NAME))
		;
	}
}