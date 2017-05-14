package com.anhvu.foody.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.Review;
import com.anhvu.foody.repository.ReviewRepository;
import com.anhvu.foody.service.ReviewService;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	private ReviewRepository ReviewRepository;

	@Override
	public Iterable<Review> findAll() {
		// TODO Auto-generated method stub
		return ReviewRepository.findAll();
	}

	@Override
	public List<Review> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review findOne(Integer id) {
		// TODO Auto-generated method stub
		return ReviewRepository.findOne(id);
	}

	@Override
	public void save(Review review) {
		// TODO Auto-generated method stub
		ReviewRepository.save(review);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		ReviewRepository.delete(id);
	}

	@Override
	public boolean isExists(Review review) {
		// TODO Auto-generated method stub
		return ReviewRepository.findOne(review.getId())!=null ? true : false;
	}

	@Override
	public Review findByValue(Integer value) {
		// TODO Auto-generated method stub
		return  ReviewRepository.isExists(value);
	}

	

}
