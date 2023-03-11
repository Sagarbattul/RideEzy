package com.mycode.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycode.blog.entities.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer>{

//	@Query("select (cast(avg(no_of_stars) as decimal(2,1))) as avgRating from ratings r where r.vehicle.id=:vehicleId")
//	int findAvgRatingByVehicle(@Param("vehicleId") Integer vehicleId);
	
//	@Query("select v from Vehicle v where v.city=:city and v.category.id=:categoryId")
//	List<Vehicle> findByCityandCategory(@Param ("city") String city, @Param("categoryId") Integer category_id);
	
	// get average rating of the vehicle
	@Query(value = "select (cast(avg(no_of_stars) as decimal(2,1))) as avgRating from ratings r where r.vehicle_id=:vehicleId", nativeQuery = true)
	String findAvgRatingByVehicle(@Param("vehicleId") Integer vehicleId);

	// get total no of ratings of the vehicle
	@Query(value = "select count(*) as totalRatings from ratings r where r.vehicle_id=:vehicleId", nativeQuery = true)
	String findTotalNoOfRatingsByVehicle(Integer vehicleId);
	
}
