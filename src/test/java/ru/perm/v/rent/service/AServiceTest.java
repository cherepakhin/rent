package ru.perm.v.rent.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.repository.StatusRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AServiceTest {

	@Autowired
	StatusService statusService;

	@Test
	public void getRepositoryCheckAssign() {
		assertTrue(
				StatusRepository.class.isAssignableFrom(
						statusService.getRepository().getClass()));
	}

	@Test
	public void getRepositoryCheckData() {
		assertEquals(2, statusService.getRepository().findAll().size());
	}
}