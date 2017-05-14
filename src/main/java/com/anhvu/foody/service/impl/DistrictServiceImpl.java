package com.anhvu.foody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.District;
import com.anhvu.foody.repository.DistrictRepository;
import com.anhvu.foody.service.DistrictService;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
	
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public Iterable<District> findAll() {
		// TODO Auto-generated method stub
		return districtRepository.findAll();
	}

	@Override
	public List<District> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public District findOne(int id) {
		// TODO Auto-generated method stub
		return districtRepository.findOne(id);
	}

	@Override
	public void save(District type) {
		// TODO Auto-generated method stub
		districtRepository.save(type);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		districtRepository.delete(id);
	}

	@Override
	public List<District> findByName(String name) {
		// TODO Auto-generated method stub
		return districtRepository.findByName(name);
	}

	@Override
	public Page<District> getDistrict(String keyword,Integer pageNumber) {
		   PageRequest request =
		            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
		return districtRepository.nameContain(keyword, request);
	}

	@Override
	public List<District> findByCity(int cityId) {
		// TODO Auto-generated method stub
		return districtRepository.findByCity(cityId);
	}

}
