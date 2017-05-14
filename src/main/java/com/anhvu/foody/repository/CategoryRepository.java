package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByName(String name);
	
	@Query(value = "SELECT c FROM Category c WHERE c.value = :value")
	Category findByValue(@Param("value")Integer value);
}
