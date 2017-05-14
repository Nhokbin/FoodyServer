package com.anhvu.foody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.Category;
import com.anhvu.foody.repository.CategoryRepository;
import com.anhvu.foody.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Iterable<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findOne(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findOne(id);
	}

	@Override
	public void save(Category type) {
		// TODO Auto-generated method stub
		categoryRepository.save(type);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		categoryRepository.delete(id);
	}

	@Override
	public Category findByValue(int value) {
		// TODO Auto-generated method stub
		return categoryRepository.findByValue(value);
	}

}
