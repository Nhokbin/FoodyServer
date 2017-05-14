package com.anhvu.foody.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anhvu.foody.entity.City;

public interface CityService {
	
	Iterable<City> findAll();
	
	List<City> findByName(String name);
	
	List<City> search();
	
	City findOne(int id);
	
	void save(City city);
	
	void delete(int id);


	Page<City> getCity(String keyword, Integer pageNumber);
}
