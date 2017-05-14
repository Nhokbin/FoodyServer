package com.anhvu.foody.service;

import java.util.List;

import com.anhvu.foody.entity.Review;
import com.anhvu.foody.entity.User;

public interface ReviewService {
	
	Iterable<Review> findAll();
	
	List<Review> search();
	
	Review findOne(Integer id);
	
	boolean isExists(Review review);
	
	void save(Review review);
	
	void delete(int id);

	Review findByValue(Integer value);
}
