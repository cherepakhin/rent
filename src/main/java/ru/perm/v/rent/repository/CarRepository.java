package ru.perm.v.rent.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.perm.v.rent.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

	// Убрана проблема N+1
	@Override
	@Query("select c " +
			"from Car c " +
			"left join fetch c.model " +
			"left join fetch c.rentalPoint " +
			"left join fetch c.status " +
			"where c.label = :label")
	Car getOne(String label);

	// Убрана проблема N+1
	@Override
	@Query("select c " +
			"from Car c " +
			"left join fetch c.model " +
			"left join fetch c.rentalPoint " +
			"left join fetch c.status " +
			"where c.label = :label")
	Optional<Car> findById(String label);

	@Override
	@Query("select distinct c " +
			"from Car c " +
			"left join fetch c.model " +
			"left join fetch c.rentalPoint " +
			"left join fetch c.status ")
	List<Car> findAll();
}
