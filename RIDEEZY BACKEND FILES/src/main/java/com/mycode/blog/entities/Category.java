package com.mycode.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="vehicle_categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name="title", length = 100, nullable=false)	
	private String categoryTitle;
	
	@Column(name="description")
	private String categoryDescription;
	
	//@JsonManagedReference
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Vehicle> vehicles = new ArrayList<>();
	
	 	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	@JsonIgnore
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
