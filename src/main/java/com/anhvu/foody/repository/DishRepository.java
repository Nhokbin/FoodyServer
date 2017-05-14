package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;
import com.anhvu.foody.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {

	List<Dish> findByName(String name);
	
	Dish findByValue(Integer value);
	
	@Query(value = "SELECT d FROM Dish d WHERE d.name like %:keyword%")
	Page<Dish> nameContain(@Param("keyword")String keyword, Pageable pageable);
	
	@Query(value = "SELECT d FROM Dish d WHERE d.streetDish.district.city.id = :cityId")
	Page<Dish> findByCity(@Param("cityId")Integer cityId, Pageable pageable);
	@Query(value = "SELECT d FROM Dish d WHERE d.categoryDish.id = :categoryId")
	Page<Dish> findByCategory(@Param("categoryId")int categoryId, Pageable request);
	
	@Query(value = "SELECT d FROM Dish d WHERE d.name like :name AND d.address LIKE :address")
	Dish isExists(@Param("name")String name, @Param("address")String address);

	@Query(value = "SELECT d FROM Dish d WHERE d.value = :value")
	Dish isExists(@Param("value")Integer value);

	

}
