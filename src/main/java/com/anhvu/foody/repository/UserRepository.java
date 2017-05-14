package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;
import com.anhvu.foody.entity.Item;
import com.anhvu.foody.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByValue(Integer value);

	List<User> findByName(String name);

	@Query(value = "SELECT u FROM User u WHERE u.name like %:keyword%")
	Page<District> nameContain(@Param("keyword") String keyword, Pageable pageable);

	@Query(value = "SELECT u FROM User u WHERE u.value = :value")
	User isExists(@Param("value") Integer value);

	List<User> findByEmail(String email);

	@Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
	User checkLogin(@Param("email")String email, @Param("password")String password);

}
