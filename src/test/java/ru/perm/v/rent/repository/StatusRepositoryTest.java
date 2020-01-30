package ru.perm.v.rent.repository;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.perm.v.rent.model.Status;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatusRepositoryTest {

	@Autowired
	StatusRepository statusRepository;

	@Test
	public void getByName() {
		Status status = statusRepository.getByName("Свободен");
		assertEquals(Status.FREE, status.getId());
	}
}