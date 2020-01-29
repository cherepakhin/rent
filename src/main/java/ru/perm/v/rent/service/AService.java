package ru.perm.v.rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AService<ENTITY, KEY> {

	@Autowired
	protected JpaRepository<ENTITY, KEY> repository;

	public JpaRepository<ENTITY, KEY> getRepository() {
		return repository;
	}
}
