package com.mycode.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.mycode.blog.entities.Driver;
import com.mycode.blog.entities.DriverCategory;
import com.mycode.blog.entities.User;
import com.mycode.blog.exceptions.ResourceNotFoundException;
import com.mycode.blog.payloads.DriverDto;
import com.mycode.blog.payloads.DriverResponse;
import com.mycode.blog.payloads.VehicleDto;
import com.mycode.blog.payloads.VehicleResponse;
import com.mycode.blog.repositories.DriverCategoryRepo;
import com.mycode.blog.repositories.DriverRepo;
import com.mycode.blog.repositories.UserRepo;
import com.mycode.blog.services.DriverService;

@Component
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DriverCategoryRepo driverCategoryRepo;
	
	
	//add or enrolling new driver
	@Override
	public DriverDto enrollAsDriver(@Valid DriverDto driverDto, Integer userId, Integer d_categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		DriverCategory driverCategory = this.driverCategoryRepo.findById(d_categoryId).orElseThrow(()-> new ResourceNotFoundException("driver category", "driver category id", d_categoryId));
		
		Driver driver = this.modelMapper.map(driverDto, Driver.class);
		driver.setDriverCategory(driverCategory);
		driver.setUser(user);
		
		
		Driver newDriver = this.driverRepo.save(driver);
		
		return this.modelMapper.map(newDriver, DriverDto.class);
		
	}

	//deleting driver from db
	@Override
	public void deleteDriver(Integer driverId) {
		Driver driver = this.driverRepo.findById(driverId).orElseThrow(()-> new ResourceNotFoundException("driver", "driver id", driverId));
		this.driverRepo.delete(driver);
	}

	//update driver
	@Override
	public DriverDto updateDriver(DriverDto driverDto, Integer driverId) {
		Driver getCurrentDriver = this.driverRepo.findById(driverId).orElseThrow(()-> new ResourceNotFoundException("driver", "driver id", driverId));
		getCurrentDriver.setD_altMobNo(driverDto.getD_altMobNo());
		getCurrentDriver.setD_bloodGroup(driverDto.getD_bloodGroup());
		getCurrentDriver.setD_education(driverDto.getD_education());
		getCurrentDriver.setD_knownLanguages(driverDto.getD_knownLanguages());
		getCurrentDriver.setD_ridingExperience(driverDto.getD_ridingExperience());
		getCurrentDriver.setDriverCategory(driverDto.getDriverCategory());
		getCurrentDriver.setD_firstName(driverDto.getD_firstName());
		getCurrentDriver.setD_lastName(driverDto.getD_lastName());
		getCurrentDriver.setD_address(driverDto.getD_address());
		getCurrentDriver.setD_city(driverDto.getD_city());
		getCurrentDriver.setD_dob(driverDto.getD_dob());
		getCurrentDriver.setD_gender(driverDto.getD_gender());
		getCurrentDriver.setDriverImage(driverDto.getDriverImage());
		getCurrentDriver.setDriverFitnessCertificateImage(driverDto.getDriverFitnessCertificateImage());
		getCurrentDriver.setDriverAgreementImage(driverDto.getDriverAgreementImage());
		getCurrentDriver.setDrivingLicenseImage(driverDto.getDrivingLicenseImage());
		getCurrentDriver.setAbout(driverDto.getAbout());
		
		Driver updatedDriver = this.driverRepo.save(getCurrentDriver);
		return this.modelMapper.map(updatedDriver, DriverDto.class);
	}

	//get all drivers
	@Override
	public List<Driver> getAllDrivers() {
		List<Driver> allDrivers = this.driverRepo.findAll();
		return allDrivers;
	}

	// get driver by id
	@Override
	public DriverDto getDriverById(Integer driverId) {
		Driver driver = this.driverRepo.findById(driverId).orElseThrow(()-> new ResourceNotFoundException("driver", "driver id", driverId));
		return this.modelMapper.map(driver, DriverDto.class);
	}

	//get drivers by city and category
	@Override
	public List<DriverDto> getDriversByCityAndCategory(String d_city, Integer d_categoryId) {
		List<Driver> drivers = this.driverRepo.findByCityandCategory(d_city,d_categoryId);
		List<DriverDto> driverDtos = drivers.stream().map((driver)->this.modelMapper.map(driver, DriverDto.class)).collect(Collectors.toList());
		return driverDtos;
	}

	//get drivers by city and category with pagination
	@Override
	public DriverResponse getAllDriversByCityAndCategoryWithPagination(Integer pageNumber, Integer pageSize,
			String sortBy, String sortDir, String d_city, Integer d_categoryId) {
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
			{
				sort=Sort.by(sortBy).ascending();
			}else {
				sort=Sort.by(sortBy).descending();
			}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Driver> pageDriver = this.driverRepo.findByCityandCategoryWithPagination(d_city, d_categoryId,p);
		List<Driver> allDrivers = pageDriver.getContent();

		List<DriverDto> driverDtos = allDrivers.stream().map((driver)->this.modelMapper.map(driver, DriverDto.class)).collect(Collectors.toList());
		
		DriverResponse driverResponse = new DriverResponse();
		
		
		
		driverResponse.setContent(driverDtos);
		driverResponse.setPageNumber(pageDriver.getNumber());
		driverResponse.setPageSize(pageDriver.getSize());
		driverResponse.setTotalElements(pageDriver.getTotalElements());
		driverResponse.setTotalPages(pageDriver.getTotalPages());
		driverResponse.setLastPage(pageDriver.isLast());
		return driverResponse;
		
	}

	//get drivers added by user with user id
	@Override
	public List<Driver> getDriversByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		
		List<Driver> drivers = this.driverRepo.findByUser(user);
		return drivers.stream().collect(Collectors.toList());
	}

	// get no of drivers added by user
	@Override
	public Integer getNoOfDriversAddedByUser(Integer userId) {
		Integer drivers = this.driverRepo.noOfDriversAddedByUser(userId);
		return drivers;
	}

	// get all drivers added by user with pagination
	@Override
	public DriverResponse getAllDriversAddedByUserWithPagination(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, Integer userId) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
			{
				sort=Sort.by(sortBy).ascending();
			}else {
				sort=Sort.by(sortBy).descending();
			}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Driver> pageDriver = this.driverRepo.findAllDriversAddedByUserWithPagination(userId,p);
		
		List<Driver> allDrivers = pageDriver.getContent();

		List<DriverDto> driverDtos = allDrivers.stream().map((driver)->this.modelMapper.map(driver, DriverDto.class)).collect(Collectors.toList());

		
		DriverResponse driverResponse = new DriverResponse();
		driverResponse.setContent(driverDtos);
		driverResponse.setPageNumber(pageDriver.getNumber());
		driverResponse.setPageSize(pageDriver.getSize());
		driverResponse.setTotalElements(pageDriver.getTotalElements());
		driverResponse.setTotalPages(pageDriver.getTotalPages());
		driverResponse.setLastPage(pageDriver.isLast());
		return driverResponse;
	}
	
	
	
//	public List<Vehicle> getVehiclesByUser(Integer userId) {
//		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
//		
//		List<Vehicle> vehicles = this.vehicleRepo.findByUser(user);
//		return vehicles.stream().collect(Collectors.toList());
//	}
	
	
	
	
	

}
