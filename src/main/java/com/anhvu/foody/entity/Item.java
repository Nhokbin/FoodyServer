package com.anhvu.foody.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="items")
public class Item implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String address;
	
	private Integer totalReviews;
	
	private String image;
	
	private Double avgrating;
	
	private Integer totalPictures;
	
	private String bookingUrl;
	
	private Boolean hasMemberCard;
	private Boolean hasPromotion;
	private Integer foodyId;
	private Boolean isAds;
	private Boolean isBooking;
	private Boolean isDelivery;
	private Boolean isRestaurantCollection;
	
	private String latitude;
	private String longitude;
	
	private String resUrlAlbums;
	private String resUrlReviews;
	
	private Integer value;
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=Category.class)
	@JoinColumn(name="categoryId")
	@JsonBackReference
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="streetId")
	@JsonBackReference
	private Street street;
	
	
	
	@OneToMany(mappedBy = "item" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Review> reviews;
	
	public Item() {
		super();
	}

	
	public Item(Integer id, String name, String address, Integer totalReviews, String image, Double avgrating,
			Integer totalPictures, String bookingUrl, Boolean hasMemberCard, Boolean hasPromotion, Integer foodyId,
			Boolean isAds, Boolean isBooking, Boolean isDelivery, Boolean isRestaurantCollection, String latitude,
			String longitude, String resUrlAlbums, String resUrlReviews, Integer value, Category category, Street street,
			List<Review> reviews) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.totalReviews = totalReviews;
		this.image = image;
		this.avgrating = avgrating;
		this.totalPictures = totalPictures;
		this.bookingUrl = bookingUrl;
		this.hasMemberCard = hasMemberCard;
		this.hasPromotion = hasPromotion;
		this.foodyId = foodyId;
		this.isAds = isAds;
		this.isBooking = isBooking;
		this.isDelivery = isDelivery;
		this.isRestaurantCollection = isRestaurantCollection;
		this.latitude = latitude;
		this.longitude = longitude;
		this.resUrlAlbums = resUrlAlbums;
		this.resUrlReviews = resUrlReviews;
		this.value = value;
		this.category = category;
		this.street = street;
		this.reviews = reviews;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getAvgrating() {
		return avgrating;
	}

	public void setAvgrating(Double avgrating) {
		this.avgrating = avgrating;
	}

	public Integer getTotalPictures() {
		return totalPictures;
	}

	public void setTotalPictures(Integer totalPictures) {
		this.totalPictures = totalPictures;
	}

	public String getBookingUrl() {
		return bookingUrl;
	}

	public void setBookingUrl(String bookingUrl) {
		this.bookingUrl = bookingUrl;
	}

	public Boolean getHasMemberCard() {
		return hasMemberCard;
	}

	public void setHasMemberCard(Boolean hasMemberCard) {
		this.hasMemberCard = hasMemberCard;
	}

	public Boolean getHasPromotion() {
		return hasPromotion;
	}

	public void setHasPromotion(Boolean hasPromotion) {
		this.hasPromotion = hasPromotion;
	}

	public Integer getFoodyId() {
		return foodyId;
	}

	public void setFoodyId(Integer foodyId) {
		this.foodyId = foodyId;
	}

	public Boolean getIsAds() {
		return isAds;
	}

	public void setIsAds(Boolean isAds) {
		this.isAds = isAds;
	}

	public Boolean getIsBooking() {
		return isBooking;
	}

	public void setIsBooking(Boolean isBooking) {
		this.isBooking = isBooking;
	}

	public Boolean getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(Boolean isDelivery) {
		this.isDelivery = isDelivery;
	}

	public Boolean getIsRestaurantCollection() {
		return isRestaurantCollection;
	}

	public void setIsRestaurantCollection(Boolean isRestaurantCollection) {
		this.isRestaurantCollection = isRestaurantCollection;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getResUrlAlbums() {
		return resUrlAlbums;
	}

	public void setResUrlAlbums(String resUrlAlbums) {
		this.resUrlAlbums = resUrlAlbums;
	}

	public String getResUrlReviews() {
		return resUrlReviews;
	}

	public void setResUrlReviews(String resUrlReviews) {
		this.resUrlReviews = resUrlReviews;
	}

	

	public Integer getValue() {
		return value;
	}


	public void setValue(Integer value) {
		this.value = value;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " " + address + " " + totalReviews + " " + totalPictures + " " + latitude + " " + longitude;
	}
	
}
