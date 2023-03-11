package com.mycode.blog.services;

import com.mycode.blog.entities.Rating;

public interface RatingService {

	Rating addRating(Rating rating, Integer vehicleId);

	void deleteRating(Integer ratingId);

	String getAvgRating(Integer vehicleId);

	String getTotalNoOfRatings(Integer vehicleId);

}
