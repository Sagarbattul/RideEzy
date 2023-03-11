package com.mycode.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycode.blog.entities.Rating;
import com.mycode.blog.entities.Vehicle;
import com.mycode.blog.exceptions.ResourceNotFoundException;
import com.mycode.blog.repositories.RatingRepo;
import com.mycode.blog.repositories.VehicleRepo;
import com.mycode.blog.services.RatingService;

@Component
public class RatingServiceImpl implements RatingService{

	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Autowired
	private RatingRepo ratingRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Rating addRating(Rating rating, Integer vehicleId) {
		Vehicle vehicle = this.vehicleRepo.findById(vehicleId).orElseThrow(()-> new ResourceNotFoundException("vehicle", "vehicle id", vehicleId));
		rating.setVehicle(vehicle);
		this.ratingRepo.save(rating);
		return rating;	
	}

	@Override
	public void deleteRating(Integer ratingId) {
		Rating rating = this.ratingRepo.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("rating", "rating id",  ratingId));
		this.ratingRepo.delete(rating);
		
	}

	// get average rating by vehicle
	@Override
	public String getAvgRating(Integer vehicleId) {
		String avgRating = this.ratingRepo.findAvgRatingByVehicle(vehicleId);
		return avgRating;
	}

	// get total no of ratings by vehicle
	@Override
	public String getTotalNoOfRatings(Integer vehicleId) {
		String totalRatings = this.ratingRepo.findTotalNoOfRatingsByVehicle(vehicleId);
		return totalRatings;
	}
	
	
	
	
	

}
