package com.anhvu.foody.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anhvu.foody.entity.Item;
import com.anhvu.foody.entity.Street;

public interface StreetService {
	
	Iterable<Street> findAll();
	
	List<Street> findByName(String name);
	
	List<Street> search();
	
	Street findOne(String id);
	
	boolean isExists(Street street);
	
	void save(Street street);
	
	void delete(int id);


}
