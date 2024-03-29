package ru.perm.v.rent.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import ru.perm.v.rent.model.Car;
import ru.perm.v.rent.service.CarService;

@RunWith(SpringRunner.class)
@WebMvcTest(CarCtrl.class)
@ActiveProfiles("test")
public class CarCtrlTest {

	private final static ObjectMapper objectMapper = new ObjectMapper();
	private static final MediaType MEDIA_TYPE_JSON = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			StandardCharsets.UTF_8);

	@MockBean
	CarService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void create() throws Exception {
		final String LABEL = "a241cc59";

		String json = String.format("{"
				+ "\"label\": \"%s\","
				+ "\"model\": \"\","
				+ "\"rentalPoint\": \"\""
				+ "}", LABEL);

		Car car = new Car();
		car.setLabel("a241cc59");
		given(this.service.saveByDTO(any()))
				.willReturn(car);

		this.mockMvc
				.perform(put("/car")
						.contentType(MEDIA_TYPE_JSON)
						.content(json)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.label").value(LABEL))
		;

	}

	@Test
	public void getFreeForRent() throws Exception {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("0"));
		cars.add(new Car("1"));
		given(this.service.getFreeForRent(any()))
				.willReturn(cars);

		this.mockMvc
				.perform(get("/car/free")
						.contentType(MEDIA_TYPE_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(2)))
		;
	}
}