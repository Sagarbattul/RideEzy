package com.mycode.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycode.blog.entities.Driver;
import com.mycode.blog.entities.User;

public interface DriverRepo extends JpaRepository<Driver, Integer>{

	@Query("select d from Driver d where d.d_city=:d_city and d.driverCategory.d_categoryId=:d_categoryId")
	List<Driver> findByCityandCategory(@Param("d_city") String d_city, @Param("d_categoryId") Integer d_categoryId);

	@Query("select d from Driver d where d.d_city=:d_city and d.driverCategory.d_categoryId=:d_categoryId")
	Page<Driver> findByCityandCategoryWithPagination(String d_city, Integer d_categoryId, Pageable pageable);

	List<Driver> findByUser(User user);

//	@Query("select d from Driver d where d.city=:d_city and d.driverCategory.id=:driver_category_id")
//	List<Driver> findByCityandCategory (@Param ("city") String city, @Param("categoryId") Integer category_id);
	
	// get no of drivers added by user
	@Query(value = "select count(*) from drivers d where d.user_id=:userId", nativeQuery = true)
	int noOfDriversAddedByUser(@Param("userId") Integer userId);

	// get drivers added by user with pagination
	@Query(value= "select d from Driver d where d.user.id=:userId")
	Page<Driver> findAllDriversAddedByUserWithPagination(@Param("userId") Integer userId, Pageable pageable);
}
