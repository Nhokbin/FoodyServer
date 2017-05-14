package com.anhvu.foody.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.anhvu.foody.entity.Item;

public interface ItemService {
	
	Iterable<Item> findAll();
	
	List<Item> findByCity(Integer cityId, Integer page);
	List<Item> findByDistrict(int parseInt, int page);

	List<Item> findByCategory(int categoryId, int page);
	List<Item> findByNew(int page);

	List<Item> findByHot(int page);
	List<Item> findByCategoryHot(int categoryId, int page);

	List<Item> findByMemberCard(int page);
	List<Item> findByCategoryMemberCard(int categoryId, int page);
	
	List<Item> findByCategoryBooking(int categoryId, int page);
	List<Item> findByBooking(int page);
	
	List<Item> findByDelivery(int page);
	List<Item> findByCategoryDelivery(int categoryId, int page);
	
	List<Item> findByPromotion(int page);
	List<Item> findByCategoryPromotion(int categoryId, int page);

	
	List<Item> findByName(String name);
		
	List<Item> search();
	
	Item findOne(int id);
	
	void save(Item item);
	
	void delete(int id);

	Page<Item> getItem(String keyword, Integer pageNumber);
	
	boolean isExists(Item item);
	
	Item findByValue(Integer value);
	
	
}
