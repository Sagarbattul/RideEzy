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
@Table(name="driver_categories")
@NoArgsConstructor
@Getter
@Setter
public class DriverCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int d_categoryId;
	
	@Column(name="driverCategoryTitle", length = 100, nullable=false)	
	private String d_categoryTitle;
	
	@Column(name="driverCategoryDescription")
	private String d_categoryDescription;
	
	//@JsonManagedReference
	@OneToMany(mappedBy = "driverCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Driver> drivers = new ArrayList<>();

	public List<Driver> getDrivers() {
		return drivers;
	}

	
	@JsonIgnore
	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
	
	
}
