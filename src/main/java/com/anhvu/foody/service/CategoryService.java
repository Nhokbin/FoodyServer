package com.anhvu.foody.service;

import java.util.List;

import com.anhvu.foody.entity.Category;

public interface CategoryService {
	
	Iterable<Category> findAll();
	
	List<Category> search();
	
	Category findByValue(int value);
	Category findOne(int id);
	
	void save(Category category);
	
	void delete(int id);
}
