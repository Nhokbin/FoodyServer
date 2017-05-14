package com.anhvu.foody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.repository.CityRepository;
import com.anhvu.foody.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {
	
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public Iterable<City> findAll() {
		// TODO Auto-generated method stub
		return cityRepository.getListCities();
	}

	@Override
	public List<City> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City findOne(int id) {
		// TODO Auto-generated method stub
		return cityRepository.findOne(id);
	}

	@Override
	public void save(City type) {
		// TODO Auto-generated method stub
		cityRepository.save(type);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		cityRepository.delete(id);
	}

	@Override
	public List<City> findByName(String name) {
		// TODO Auto-generated method stub
		return cityRepository.findByName(name);
	}

	@Override
	public Page<City> getCity(String keyword,Integer pageNumber) {
		   PageRequest request =
		            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
		return cityRepository.nameContain(keyword, request);
	}

}
