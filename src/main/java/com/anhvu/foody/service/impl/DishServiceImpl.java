package com.anhvu.foody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.Dish;
import com.anhvu.foody.repository.DishRepository;
import com.anhvu.foody.service.DishService;

@Service
@Transactional
public class DishServiceImpl implements DishService {
	
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	private DishRepository DishRepository;

	@Override
	public Iterable<Dish> findAll() {
		// TODO Auto-generated method stub
		return DishRepository.findAll();
	}

	@Override
	public List<Dish> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dish findOne(int id) {
		// TODO Auto-generated method stub
		return DishRepository.findOne(id);
	}

	@Override
	public void save(Dish dish) {
		// TODO Auto-generated method stub
		DishRepository.saveAndFlush(dish);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		DishRepository.delete(id);
	}

	@Override
	public List<Dish> findByName(String name) {
		// TODO Auto-generated method stub
		return DishRepository.findByName(name);
	}

	@Override
	public Page<Dish> getDish(String keyword,Integer pageNumber) {
		   PageRequest request =
		            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
		return DishRepository.nameContain(keyword, request);
	}

	@Override
	public boolean isExists(Dish dish) {
		return DishRepository.findByValue((dish.getValue())) != null ? true : false;
	}

	@Override
	public Dish findByValue(Integer value) {
		// TODO Auto-generated method stub
		return DishRepository.isExists(value);
	}


	@Override
	public List<Dish> findByCity(Integer cityId, Integer page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return DishRepository.findByCity(cityId,request).getContent();
	}

	
	@Override
	public List<Dish> findByNew(int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return DishRepository.findAll(request).getContent();
	}
	
	@Override
	public List<Dish> findByCategoryNew(int categoryId, int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return DishRepository.findByCategory(categoryId,request).getContent();
	}

	@Override
	public List<Dish> findByHot(int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "totalReviews");
		return DishRepository.findAll(request).getContent();
	}

	@Override
	public List<Dish> findByDistrict(Integer districtId, Integer page) {
		// TODO Auto-generated method stub
		return null;
	}


}
