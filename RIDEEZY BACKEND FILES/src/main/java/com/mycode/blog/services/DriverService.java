package com.mycode.blog.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.mycode.blog.entities.Driver;
import com.mycode.blog.payloads.DriverDto;
import com.mycode.blog.payloads.DriverResponse;

@Service
public interface DriverService {

	DriverDto enrollAsDriver(@Valid DriverDto driverDto, Integer userId, Integer d_categoryId);

	void deleteDriver(Integer driverId);

	DriverDto updateDriver(DriverDto driverDto, Integer driverId);

	List<Driver> getAllDrivers();

	DriverDto getDriverById(Integer driverId);

	List<DriverDto> getDriversByCityAndCategory(String d_city, Integer d_categoryId);

	DriverResponse getAllDriversByCityAndCategoryWithPagination(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, String d_city, Integer d_categoryId);

	List<Driver> getDriversByUser(Integer userId);

	//get no of drivers added by user
	Integer getNoOfDriversAddedByUser(Integer userId);

	// get all drivers added by user with pagination
	DriverResponse getAllDriversAddedByUserWithPagination(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, Integer userId);

	

	
	
}
