package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	List<City> findByName(String name);
	
	@Query(value = "SELECT c FROM City c WHERE c.id < 64")
	List<City> getListCities();
	
	/*@Query(value = "SELECT * FROM cities WHERE name = ?1", 
			countQuery = "SELECT count(*) FROM cities WHERE name = ?1", 
			nativeQuery = true)
	Page<City> page(String name, Pageable pageable);*/
	
	@Query(value = "SELECT c FROM City c WHERE c.name like %:keyword%")
	Page<City> nameContain(@Param("keyword")String keyword, Pageable pageable);
}
