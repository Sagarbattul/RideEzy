package com.mycode.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="driver_ratings")
@Getter
@Setter
@NoArgsConstructor
public class DriverRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int d_ratingId;
	
	private int d_noOfStars;
	
//	@JsonBackReference
	@ManyToOne
	private Driver driver;
}
