package com.anhvu.foody.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name= "categories")
public class Category implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String image;
	
	private Integer value;
	
	private Integer type;
	
	@OneToMany(targetEntity=Item.class,mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval=true)
	@JsonIgnore
	private List<Item> items;
	
	@OneToMany(targetEntity=Dish.class,mappedBy = "categoryDish", fetch = FetchType.EAGER,orphanRemoval=true)
	@JsonIgnore
	private List<Dish> dishs;
	

	public Category() {
		super();
	}

	
	public Category(Integer id, String name, String image, Integer value, Integer type, List<Item> items,
			List<Dish> dishs) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.value = value;
		this.type = type;
		this.items = items;
		this.dishs = dishs;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}


	public List<Dish> getDishs() {
		return dishs;
	}


	public void setDishs(List<Dish> dishs) {
		this.dishs = dishs;
	}
	
	
}
