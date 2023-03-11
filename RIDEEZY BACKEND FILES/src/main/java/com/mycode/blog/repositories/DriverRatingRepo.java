package com.mycode.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycode.blog.entities.DriverRating;

public interface DriverRatingRepo extends JpaRepository<DriverRating, Integer>{

	//get average rating for driver
	@Query(value = "select (cast(avg(d_no_of_stars) as decimal(2,1))) as avgRating from driver_ratings r where r.driver_d_id=:driverId", nativeQuery = true)
	String findAvgRatingByDriver(@Param("driverId") Integer driverId);

	
	// get total no of ratings by driver
	@Query(value = "select count(*) from driver_ratings r where r.driver_d_id=:driverId", nativeQuery = true)
	String findTotalNoOfRatingsByDriver(Integer driverId);
}
