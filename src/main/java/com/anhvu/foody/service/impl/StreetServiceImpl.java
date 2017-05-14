package com.anhvu.foody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.Street;
import com.anhvu.foody.repository.StreetRepository;
import com.anhvu.foody.service.StreetService;

@Service
@Transactional
public class StreetServiceImpl implements StreetService {
	
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	private StreetRepository streetRepository;

	@Override
	public Iterable<Street> findAll() {
		// TODO Auto-generated method stub
		return streetRepository.findAll();
	}

	@Override
	public List<Street> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Street findOne(String id) {
		// TODO Auto-generated method stub
		return streetRepository.findOne(id);
	}

	@Override
	public void save(Street type) {
		// TODO Auto-generated method stub
		streetRepository.save(type);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		streetRepository.delete(id);
	}

	@Override
	public List<Street> findByName(String name) {
		// TODO Auto-generated method stub
		return streetRepository.findByName(name);
	}

	@Override
	public boolean isExists(Street street) {
		// TODO Auto-generated method stub
		return streetRepository.findOne(street.getId()) !=null ? true : false;
	}

	

}
