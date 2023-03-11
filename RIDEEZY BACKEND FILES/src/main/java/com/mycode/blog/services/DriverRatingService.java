package com.mycode.blog.services;

import com.mycode.blog.entities.DriverRating;

public interface DriverRatingService {

	DriverRating addDriverRating(DriverRating driverRating, Integer driverId);

	void deleteDriverRating(Integer d_ratingId);

	// get average rating of driver
	String getAvgRatingByDriver(Integer driverId);

	// get total no of ratings by driver
	String getTotalNoOfRatingsByDriver(Integer driverId);

}
