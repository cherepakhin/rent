package ru.perm.v.rent.dto;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.io.IOException;
import org.junit.Test;

public class CarDTOTest {

	final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void convertFromJson() throws IOException {
		final String LABEL = "a241cc59";
		final String MODEL = "Toyota";
		final String RENTAL_POINT = "Пункт-1";

		String json = String.format("{"
				+ "\"label\": \"%s\","
				+ "\"model\": \"%s\","
				+ "\"rentalPoint\": \"%s\""
				+ "}", LABEL, MODEL, RENTAL_POINT);
		CarDTO carDTO = objectMapper.readValue(json, CarDTO.class);
		assertEquals(LABEL, carDTO.getLabel());
		assertEquals(MODEL, carDTO.getModel());
		assertEquals(RENTAL_POINT, carDTO.getRentalPoint());
		assertEquals("", carDTO.getStatus());
	}

	@Test(expected = UnrecognizedPropertyException.class)
	public void convertException() throws IOException {
		final String LABEL = "a241cc59";
		final String MODEL = "Toyota";
		final String RENTAL_POINT = "Пункт-1";

		String json = String.format("{"
				+ "\"label\": \"%s\","
				+ "\"model__1\": \"%s\"," // Здесь ошибка. Нужно поле "model"
				+ "\"rentalPoint\": \"%s\""
				+ "}", LABEL, MODEL, RENTAL_POINT);
		objectMapper.readValue(json, CarDTO.class);
	}
}