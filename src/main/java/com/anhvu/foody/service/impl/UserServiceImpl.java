package com.anhvu.foody.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.User;
import com.anhvu.foody.repository.UserRepository;
import com.anhvu.foody.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public List<User> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public void save(User type) {
		// TODO Auto-generated method stub
		userRepository.save(type);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}

	@Override
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByName(name);
	}

	@Override
	public boolean isExists(User User) {
		// TODO Auto-generated method stub
		return userRepository.isExists(User.getValue()) !=null ? true : false;
	}

	@Override
	public User findByValue(Integer value) {
		// TODO Auto-generated method stub
		return userRepository.isExists(value);
	}

	@Override
	public boolean findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).isEmpty();
	}

	@Override
	public boolean checkLogin(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.checkLogin(email,password) != null ? true : false;
	}

	

}
