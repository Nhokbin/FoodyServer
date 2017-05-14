package com.anhvu.foody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anhvu.foody.entity.Item;
import com.anhvu.foody.repository.ItemRepository;
import com.anhvu.foody.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	private static final int PAGE_SIZE = 12;
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Iterable<Item> findAll() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}

	@Override
	public List<Item> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findOne(int id) {
		// TODO Auto-generated method stub
		return itemRepository.findOne(id);
	}

	@Override
	public void save(Item type) {
		// TODO Auto-generated method stub
		itemRepository.save(type);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		itemRepository.delete(id);
	}

	@Override
	public List<Item> findByName(String name) {
		// TODO Auto-generated method stub
		return itemRepository.findByName(name);
	}

	@Override
	public Page<Item> getItem(String keyword,Integer pageNumber) {
		   PageRequest request =
		            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
		return itemRepository.nameContain(keyword, request);
	}

	@Override
	public boolean isExists(Item item) {
		return itemRepository.findByValue((item.getValue())) != null ? true : false;
	}

	@Override
	public Item findByValue(Integer value) {
		// TODO Auto-generated method stub
		return itemRepository.isExists(value);
	}


	@Override
	public List<Item> findByCity(Integer cityId, Integer page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return itemRepository.findByCity(cityId,request).getContent();
	}
	@Override
	public List<Item> findByDistrict(int districtId, int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
		return itemRepository.findByDistrict(districtId,request).getContent();
	}
	@Override
	public List<Item> findByCategory(int categoryId, int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByCategory(categoryId,request).getContent();
	}

	@Override
	public List<Item> findByNew(int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findAll(request).getContent();
	}

	@Override
	public List<Item> findByHot(int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "totalReviews");
		return itemRepository.findAll(request).getContent();
	}
	
	@Override
	public List<Item> findByCategoryHot(int categoryId, int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "totalReviews");
		return itemRepository.findByCategory(categoryId,request).getContent();
	}

	@Override
	public List<Item> findByMemberCard(int page) {
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByMemberCard(request).getContent();
	}
	@Override
	public List<Item> findByCategoryMemberCard(int categoryId, int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByCategoryMemberCard(categoryId ,request).getContent();
	}

	
	@Override
	public List<Item> findByBooking(int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByBooking(request).getContent();
	}
	@Override
	public List<Item> findByCategoryBooking(int categoryId, int page) {
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByCategoryBooking(categoryId,request).getContent();
	}
	
	@Override
	public List<Item> findByDelivery(int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByDelivery(request).getContent();
	}
	@Override
	public List<Item> findByCategoryDelivery(int categoryId, int page) {
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByCategoryDelivery(categoryId,request).getContent();
	}

	@Override
	public List<Item> findByPromotion(int page) {
		// TODO Auto-generated method stub
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByPromotion(request).getContent();
	}

	@Override
	public List<Item> findByCategoryPromotion(int categoryId, int page) {
		 PageRequest request =
		            new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
		return itemRepository.findByCategoryPromotion(categoryId,request).getContent();
	}

	

}
