package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;
import com.anhvu.foody.entity.Street;

public interface StreetRepository extends JpaRepository<Street, Integer> {

	List<Street> findByName(String name);

	@Query(value = "SELECT s FROM Street s WHERE s.id = :id")
	Street findOne(@Param("id") String id);
	
}
