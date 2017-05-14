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
@Table(name="cities")
public class City implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private Integer count;
	
	private String url;

	private Integer dataId;
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<District> districts;

	public City() {
		super();
	}

	public City(Integer id, String name, Integer count, String url, Integer dataId, List<District> districts) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.url = url;
		this.dataId = dataId;
		this.districts = districts;
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



	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Integer getDataId() {
		return dataId;
	}



	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}



	public List<District> getDistricts() {
		return districts;
	}



	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " " + count + " " + " " + url;
	}
	
	
}
