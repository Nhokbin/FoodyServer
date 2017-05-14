package com.anhvu.foody.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anhvu.foody.entity.District;

public interface DistrictService {
	
	Iterable<District> findAll();
	
	List<District> findByName(String name);
	List<District> findByCity(int cityId);
	
	List<District> search();
	
	District findOne(int id);
	
	void save(District district);
	
	void delete(int id);


	Page<District> getDistrict(String keyword, Integer pageNumber);
}
