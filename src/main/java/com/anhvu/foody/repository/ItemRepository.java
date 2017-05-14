package com.anhvu.foody.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;
import com.anhvu.foody.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByName(String name);
	
	Item findByValue(int value);
	
	@Query(value = "SELECT i FROM Item i WHERE i.name like %:keyword%")
	Page<Item> nameContain(@Param("keyword")String keyword, Pageable pageable);
	
	@Query(value = "SELECT i FROM Item i WHERE i.street.district.city.id = :cityId")
	Page<Item> findByCity(@Param("cityId")int cityId, Pageable pageable);
	
	@Query(value = "SELECT i FROM Item i WHERE i.street.district.id = :districtId")
	Page<Item> findByDistrict(@Param("districtId")int districtId, Pageable request);
	
	
	@Query(value = "SELECT i FROM Item i WHERE i.hasMemberCard = true")
	Page<Item> findByMemberCard(Pageable request);
	@Query(value = "SELECT i FROM Item i WHERE i.hasMemberCard = true AND i.category.id = :categoryId")
	Page<Item> findByCategoryMemberCard(@Param("categoryId")int categoryId, Pageable request);
	
	@Query(value = "SELECT i FROM Item i WHERE i.isBooking = true")
	Page<Item> findByBooking(Pageable request);
	@Query(value = "SELECT i FROM Item i WHERE i.isBooking = true AND i.category.id = :categoryId")
	Page<Item> findByCategoryBooking(@Param("categoryId")int categoryId, Pageable request);
	
	
	@Query(value = "SELECT i FROM Item i WHERE i.isDelivery = true")
	Page<Item> findByDelivery(Pageable request);
	@Query(value = "SELECT i FROM Item i WHERE i.isDelivery = true AND i.category.id = :categoryId")
	Page<Item> findByCategoryDelivery(@Param("categoryId")int categoryId, Pageable request);

	@Query(value = "SELECT i FROM Item i WHERE i.hasPromotion = true")
	Page<Item> findByPromotion(Pageable request);
	@Query(value = "SELECT i FROM Item i WHERE i.hasPromotion = true AND i.category.id = :categoryId")
	Page<Item> findByCategoryPromotion(@Param("categoryId")int categoryId,Pageable request);
	
	@Query(value = "SELECT i FROM Item i WHERE i.category.id = :categoryId")
	Page<Item> findByCategory(@Param("categoryId")int categoryId, Pageable request);
	
	@Query(value = "SELECT i FROM Item i WHERE i.name like :name AND i.address LIKE :address")
	Item isExists(@Param("name")String name, @Param("address")String address);

	@Query(value = "SELECT i FROM Item i WHERE i.value = :value")
	Item isExists(@Param("value")int value);

	
	
}
