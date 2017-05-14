package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	@Query(value = "SELECT r FROM Review r WHERE r.value = :value")
	Review isExists(@Param("value")Integer value);


	
	/*@Query(value = "SELECT u FROM Review u WHERE u.value = :value")
	Review isExists(@Param("value")Integer value);*/
}
