package com.mycode.blog.services.impl;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.User;
import com.mycode.blog.entities.Vehicle;
import com.mycode.blog.exceptions.ResourceNotFoundException;
import com.mycode.blog.payloads.VehicleDto;
import com.mycode.blog.payloads.VehicleResponse;
import com.mycode.blog.repositories.CategoryRepo;
import com.mycode.blog.repositories.UserRepo;
import com.mycode.blog.repositories.VehicleRepo;

import com.mycode.blog.services.VehicleService;
import com.mycode.blog.utils.Utility;

@Component
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;

//	@Override
//	public ApiResponse<Vehicle> addNewVehicle(Vehicle vehicle) {
//		// TODO Auto-generated method stub
//		vehicleRepo.save(vehicle);
//		return null;
//	}

	@Override
	public VehicleDto getVehicleById(Integer vehicleId) {
		// TODO Auto-generated method stub
		Vehicle vehicle = this.vehicleRepo.findById(vehicleId).orElseThrow(()-> new ResourceNotFoundException("vehicle", "vehicle id", vehicleId));
		return this.modelMapper.map(vehicle, VehicleDto.class);
	}

	@Override
	public void deleteVehicle(Integer vehicleId) {
		// TODO Auto-generated method stub
		Vehicle vehicle = this.vehicleRepo.findById(vehicleId).orElseThrow(()-> new ResourceNotFoundException("vehicle", "vehicle id", vehicleId));
		this.vehicleRepo.delete(vehicle);
		
	}

	@Override
	public VehicleDto addVehicle(VehicleDto vehicleDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		
		Vehicle vehicle = this.modelMapper.map(vehicleDto, Vehicle.class);
		vehicle.setCategory(category);
		vehicle.setUser(user);
		
		Vehicle newVehicle = this.vehicleRepo.save(vehicle);
		
		return this.modelMapper.map(newVehicle, VehicleDto.class);
	}

	//get vehicle by category
	@Override
	public List<Vehicle> getVehiclesByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "category id", categoryId));
		//List<Vehicle> vehicles = this.vehicleRepo.findByCategory(category);
		List<Vehicle> vehicles = this.vehicleRepo.findByCategory(category);
		
		
		return vehicles.stream().collect(Collectors.toList());
	}

	
	// get all vehicles
	@Override
	public List<VehicleDto> getAllVehicles() {
		List<Vehicle> allVehicles = this.vehicleRepo.findAll();
		List<VehicleDto> vehicleDtos = allVehicles.stream().map((vehicle)->this.modelMapper.map(vehicle,VehicleDto.class)).collect(Collectors.toList());
		return vehicleDtos;
	}

	//get vehicles by user
	@Override
	public List<Vehicle> getVehiclesByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		
		List<Vehicle> vehicles = this.vehicleRepo.findByUser(user);
		return vehicles.stream().collect(Collectors.toList());
	}

	//update vehicle
	@Override
	public VehicleDto updateVehicle(VehicleDto vehicleDto, Integer vehicleId) {
		Vehicle getCurrentVehicle = this.vehicleRepo.findById(vehicleId).orElseThrow(()-> new ResourceNotFoundException("vehicle", "vehicle id", vehicleId));
		getCurrentVehicle.setModel(vehicleDto.getModel());
		getCurrentVehicle.setCity(vehicleDto.getCity());
		getCurrentVehicle.setNumber(vehicleDto.getNumber());
		getCurrentVehicle.setCategory(vehicleDto.getCategory());
		getCurrentVehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
		getCurrentVehicle.setLuggageCapacity(vehicleDto.getLuggageCapacity());
		getCurrentVehicle.setFuelType(vehicleDto.getFuelType());
		getCurrentVehicle.setTransmission(vehicleDto.getTransmission());
		getCurrentVehicle.setAirCondition(vehicleDto.getAirCondition());
		getCurrentVehicle.setMileage(vehicleDto.getMileage());
		getCurrentVehicle.setVehicleImage(vehicleDto.getVehicleImage());
		getCurrentVehicle.setVehicleRCImage(vehicleDto.getVehicleRCImage());
		getCurrentVehicle.setVehiclePUCImage(vehicleDto.getVehiclePUCImage());
		getCurrentVehicle.setVehicleInsuranceImage(vehicleDto.getVehicleInsuranceImage());
		getCurrentVehicle.setVehicleAgreementImage(vehicleDto.getVehicleAgreementImage());
		
		Vehicle updatedVehicle = this.vehicleRepo.save(getCurrentVehicle);
		
		return this.modelMapper.map(updatedVehicle, VehicleDto.class);
	}

	@Override
	public List<Vehicle> searchVehiclesByName(String keywords) {
		List<Vehicle> vehicles = this.vehicleRepo.findByModelContaining(keywords);
		return vehicles.stream().collect(Collectors.toList());
	}

	@Override
	public VehicleResponse getAllVehiclesWithPagination(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
			{
				sort=Sort.by(sortBy).ascending();
			}else {
				sort=Sort.by(sortBy).descending();
			}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Vehicle> pageVehicle = this.vehicleRepo.findAll(p);
		List<Vehicle> allVehicles = pageVehicle.getContent();
		
//		List<Vehicle> vehicles = allVehicles.stream().collect(Collectors.toList());
		List<VehicleDto> vehicleDtos = allVehicles.stream().map((vehicle)->this.modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
		
		VehicleResponse vehicleResponse = new VehicleResponse();
		
//		vehicleResponse.setContent(vehicles);
//		vehicleResponse.setPageNumber(pageVehicle.getNumber());
//		vehicleResponse.setPageSize(pageVehicle.getSize());
//		vehicleResponse.setTotalElements(pageVehicle.getTotalElements());
//		vehicleResponse.setTotalPages(pageVehicle.getTotalPages());
//		vehicleResponse.setLastPage(pageVehicle.isLast());
		
		vehicleResponse.setContent(vehicleDtos);
		vehicleResponse.setPageNumber(pageVehicle.getNumber());
		vehicleResponse.setPageSize(pageVehicle.getSize());
		vehicleResponse.setTotalElements(pageVehicle.getTotalElements());
		vehicleResponse.setTotalPages(pageVehicle.getTotalPages());
		vehicleResponse.setLastPage(pageVehicle.isLast());
		
		return vehicleResponse;
	}

	// get vehicle by city name
	@Override
	public List<VehicleDto> searchVehicleByCity(String city) {
		// TODO Auto-generated method stub
		List<Vehicle> vehicles = this.vehicleRepo.findByCityContaining(city);
		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle)-> this.modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
		
		return vehicleDtos;
	}


	// get vehicle by city and category
	@Override
	public List<VehicleDto> getVehiclesByCityandCategory(String city, Integer categoryId) {
		// TODO Auto-generated method stub
		List<Vehicle> vehicles = this.vehicleRepo.findByCityandCategory(city, categoryId);
		List<VehicleDto> vehicleDtos = vehicles.stream().map((vehicle)-> this.modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
		return vehicleDtos;
	}
	
	// get all vehicles by city and category with pagination
	@Override
	public VehicleResponse getAllVehiclesByCityAndCategoryWithPagination(Integer pageNumber, Integer pageSize,
			String sortBy, String sortDir, String city, Integer categoryId) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
			{
				sort=Sort.by(sortBy).ascending();
			}else {
				sort=Sort.by(sortBy).descending();
			}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Vehicle> pageVehicle = this.vehicleRepo.findByCityandCategoryWithPagination(city, categoryId,p);
		List<Vehicle> allVehicles = pageVehicle.getContent();
		
		List<VehicleDto> vehicleDtos = allVehicles.stream().map((vehicle)->this.modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
		
		VehicleResponse vehicleResponse = new VehicleResponse();
		
		vehicleResponse.setContent(vehicleDtos);
		vehicleResponse.setPageNumber(pageVehicle.getNumber());
		vehicleResponse.setPageSize(pageVehicle.getSize());
		vehicleResponse.setTotalElements(pageVehicle.getTotalElements());
		vehicleResponse.setTotalPages(pageVehicle.getTotalPages());
		vehicleResponse.setLastPage(pageVehicle.isLast());
		return vehicleResponse;
	}

	// get no of vehicles added by user
	@Override
	public int getNoOfVehiclesAddedByUser(Integer userId) {
		int noOfVehicles = this.vehicleRepo.NoOfVehiclesAddedByUser(userId);
		return noOfVehicles;
	}

	// get all vehicles added by user with pagination
	@Override
	public VehicleResponse getAllVehicleAddedByUserWithPagination(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, Integer userId) {
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
			{
				sort=Sort.by(sortBy).ascending();
			}else {
				sort=Sort.by(sortBy).descending();
			}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Vehicle> pageVehicle = this.vehicleRepo.findVehiclesAddedByUser(userId,p);
		List<Vehicle> allVehicles = pageVehicle.getContent();
		
		List<VehicleDto> vehicleDtos = allVehicles.stream().map((vehicle)->this.modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
		
		VehicleResponse vehicleResponse = new VehicleResponse();
		vehicleResponse.setContent(vehicleDtos);
		vehicleResponse.setPageNumber(pageVehicle.getNumber());
		vehicleResponse.setPageSize(pageVehicle.getSize());
		vehicleResponse.setTotalElements(pageVehicle.getTotalElements());
		vehicleResponse.setTotalPages(pageVehicle.getTotalPages());
		vehicleResponse.setLastPage(pageVehicle.isLast());
		return vehicleResponse;
	}
	
	
	
	
	
	
	
	

	

	


	

	

	

	
	
	

	/*
	 * @Override public ApiResponse<User> registerNewUser_2(User userDto) {
	 * if(userDto==null) return new
	 * ApiResponse("Input fields are missing !",false,402); else
	 * if(Utility.isFieldEmpty(userDto.getFirstName()) ||
	 * userDto.getFirstName().length()<4) { return new
	 * ApiResponse("FirstName should be greater than 4 characters!",false,402); }
	 * 
	 * else if(Utility.isFieldEmpty(userDto.getLastName()) ||
	 * userDto.getLastName().length()<4) { return new
	 * ApiResponse("LastName should be greater than 4 characters!",false,402); }
	 * 
	 * 
	 * //else if(Utility.isFieldEmpty(userDto.getEmail()) ||
	 * !Utility.isEmailValid(userDto.getEmail())) else
	 * if(Utility.isEmailValid(userDto.getEmail())) return new
	 * ApiResponse("Email should be in proper format !",false,402);
	 * 
	 * else if(Utility.isFieldEmpty(userDto.getPassword()) ||
	 * (userDto.getPassword().length()<3 || userDto.getPassword().length()>10))
	 * return new
	 * ApiResponse("Password should be more than 3 or less than 10 chars !",false,
	 * 402);
	 * 
	 * else if(Utility.isFieldEmpty(userDto.getMobile()) ||
	 * userDto.getMobile().length()!=10) return new
	 * ApiResponse<>("Proper Mobile Number not provided", false, 402);
	 * 
	 * else if(Utility.isFieldEmpty(userDto.getDob())) return new
	 * ApiResponse<>("Date of Birth Not Provided", false, 402);
	 * 
	 * else if(Utility.isFieldEmpty(userDto.getGender())) return new
	 * ApiResponse<>("Gender not selected", false, 402);
	 * 
	 * else if(Utility.isFieldEmpty(userDto.getAddress())) return new
	 * ApiResponse<>("Address Not Provided", false, 402);
	 * 
	 * 
	 * Optional<User> returnedUser = userRepo.findByEmail(userDto.getEmail());
	 * if(returnedUser.isPresent()) { return new
	 * ApiResponse(userDto,"User already exists !",false,402); }
	 * 
	 * 
	 * //encoded the password
	 * userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
	 * 
	 * //roles
	 * 
	 * Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
	 * userDto.getRoles().add(role); User newUser = this.userRepo.save(userDto);
	 * 
	 * TODO Auto-generated method stub this.userRepo.save(userDto); return new
	 * ApiResponse(userDto,"User registered successfully.",true,201);
	 * 
	 * }
	 */

}
