package com.anhvu.foody.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anhvu.foody.entity.Dish;

public interface DishService {
	
	Iterable<Dish> findAll();
	
	List<Dish> findByCity(Integer cityId, Integer page);
	List<Dish> findByDistrict(Integer districtId, Integer page);
	List<Dish> findByNew(int page);
	List<Dish> findByCategoryNew(int categoryId, int page);
	List<Dish> findByHot(int page);
	
	
	List<Dish> findByName(String name);
		
	List<Dish> search();
	
	Dish findOne(int id);
	
	void save(Dish dish);
	
	void delete(int id);

	Page<Dish> getDish(String keyword, Integer pageNumber);
	
	boolean isExists(Dish dish);
	
	Dish findByValue(Integer value);

	

	



	

}
