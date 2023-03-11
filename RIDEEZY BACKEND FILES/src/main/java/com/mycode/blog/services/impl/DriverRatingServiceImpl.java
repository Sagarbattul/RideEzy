package com.mycode.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycode.blog.entities.Driver;
import com.mycode.blog.entities.DriverRating;
import com.mycode.blog.exceptions.ResourceNotFoundException;
import com.mycode.blog.repositories.DriverRatingRepo;
import com.mycode.blog.repositories.DriverRepo;
import com.mycode.blog.services.DriverRatingService;

@Component
public class DriverRatingServiceImpl implements DriverRatingService{

	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private DriverRatingRepo driverRatingRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//add driver rating
	@Override
	public DriverRating addDriverRating(DriverRating driverRating, Integer driverId) {
		Driver driver = this.driverRepo.findById(driverId).orElseThrow(()-> new ResourceNotFoundException("driver", "driver id", driverId));
		driverRating.setDriver(driver);
		this.driverRatingRepo.save(driverRating);
		return driverRating;
	}

	//delete driver rating
	@Override
	public void deleteDriverRating(Integer d_ratingId) {
		DriverRating driverRating = this.driverRatingRepo.findById(d_ratingId).orElseThrow(()-> new ResourceNotFoundException("driver rating", "driver rating id",  d_ratingId));
		this.driverRatingRepo.delete(driverRating);
	}

	//get average rating of the driver
	@Override
	public String getAvgRatingByDriver(Integer driverId) {
		String driverRating = this.driverRatingRepo.findAvgRatingByDriver(driverId);
		return driverRating;
	}

	// get total no of ratings by driver
	@Override
	public String getTotalNoOfRatingsByDriver(Integer driverId) {
		String totalDriverRatings = this.driverRatingRepo.findTotalNoOfRatingsByDriver(driverId);
		return totalDriverRatings;
	}

	
	
}
