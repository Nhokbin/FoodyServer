package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {

	List<District> findByName(String name);
	
	@Query(value = "SELECT d FROM District d WHERE d.name like %:keyword%")
	Page<District> nameContain(@Param("keyword")String keyword, Pageable pageable);

	@Query(value = "SELECT d FROM District d WHERE d.city.id = :cityId ")
	List<District> findByCity(@Param("cityId")int cityId);
}
