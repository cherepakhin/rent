package ru.perm.v.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.rent.model.ModelCar;

@Repository
public interface ModelCarRepository extends JpaRepository<ModelCar, Long> {

	ModelCar getByName(String name);
}
