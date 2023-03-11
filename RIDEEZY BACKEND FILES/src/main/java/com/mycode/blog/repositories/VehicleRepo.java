package com.mycode.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.User;
import com.mycode.blog.entities.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer>{

	List<Vehicle> findByCategory(Category category);
	
	List<Vehicle> findByUser(User user);
	
	List<Vehicle> findByModelContaining(String name);

	List<Vehicle> findByCityContaining(String city);
	
	@Query("select v from Vehicle v where v.city=:city and v.category.id=:categoryId")
	List<Vehicle> findByCityandCategory(@Param ("city") String city, @Param("categoryId") Integer category_id);

	@Query("select v from Vehicle v where v.city=:city and v.category.id=:categoryId")
	Page<Vehicle> findByCityandCategoryWithPagination(@Param ("city") String city,@Param("categoryId") Integer category_id, Pageable pageable);
	
	@Query(value = "select count(*) as total_vehicles from vehicles v where v.user_id=:userId", nativeQuery = true)
	int NoOfVehiclesAddedByUser(@Param("userId") Integer userId);

	// get no of vehicles added by  user
	@Query(value = "select * from vehicles  where user_id=:userId", nativeQuery = true)
	Page<Vehicle> findVehiclesAddedByUser(@Param("userId") Integer userId, Pageable pageable);
	
	
	
}
