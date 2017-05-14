package com.anhvu.foody.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name ="reviews")
public class Review implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String content;
	
	private String url;
	
	private Integer value;
	
	private String avgRating;
	
	private String imageReview;
	
	private String nameReivew;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="userId")
	@JsonBackReference
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="itemId")
	@JsonBackReference
	private Item item;

	
	
	public Review() {
		super();
	}


	public Review(Integer id, String content, String url, Integer value, String avgRating, String imageReview,
			String nameReivew, User user, Item item) {
		super();
		this.id = id;
		this.content = content;
		this.url = url;
		this.value = value;
		this.avgRating = avgRating;
		this.imageReview = imageReview;
		this.nameReivew = nameReivew;
		this.user = user;
		this.item = item;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(String avgRating) {
		this.avgRating = avgRating;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getImageReview() {
		return imageReview;
	}


	public void setImageReview(String imageReview) {
		this.imageReview = imageReview;
	}


	public String getNameReivew() {
		return nameReivew;
	}


	public void setNameReivew(String nameReivew) {
		this.nameReivew = nameReivew;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
	
}
