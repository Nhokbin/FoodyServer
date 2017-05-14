package com.anhvu.foody.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anhvu.foody.entity.Item;
import com.anhvu.foody.entity.User;

public interface UserService {
	
	Iterable<User> findAll();
	
	List<User> findByName(String name);
	
	List<User> search();
	
	User findOne(Integer id);
	
	User findByValue(Integer value);
	
	boolean isExists(User User);
	
	void save(User user);
	
	void delete(int id);

	boolean findByEmail(String email);

	boolean checkLogin(String email, String password);


}
