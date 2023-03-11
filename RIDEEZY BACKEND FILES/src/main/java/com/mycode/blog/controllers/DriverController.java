package com.mycode.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycode.blog.config.AppConstants;
import com.mycode.blog.entities.ApiResponse;
import com.mycode.blog.entities.Driver;
import com.mycode.blog.entities.Vehicle;
import com.mycode.blog.payloads.DriverDto;
import com.mycode.blog.payloads.DriverResponse;
import com.mycode.blog.payloads.VehicleDto;
import com.mycode.blog.services.DriverService;
import com.mycode.blog.services.FileService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private FileService fileService;
	
	//add new driver
	@PostMapping("/user/{userId}/category/{d_categoryId}/enrollAsDriver")
	public ResponseEntity<DriverDto> enrollAsDriver(@Valid @RequestBody DriverDto driverDto, @PathVariable Integer userId, @PathVariable Integer d_categoryId)
	{
		DriverDto addedDriver = this.driverService.enrollAsDriver(driverDto, userId, d_categoryId);
		return new ResponseEntity<DriverDto>(addedDriver, HttpStatus.CREATED);
		
	}
	
	//delete driver
	@DeleteMapping("/deleteDriver/{driverId}")
	public ApiResponse<Driver> deleteDriver(@PathVariable Integer driverId)
	{
		this.driverService.deleteDriver(driverId);		
		//Driver driver = this.driverService.getDriverById(driverId);
		return new ApiResponse("Driver with " + driverId + " deleted successfully.",true,200);
	}
	
	//update driver
	@PutMapping("/updateDriver/{driverId}")
	public ApiResponse<DriverDto> updateDriver(@RequestBody DriverDto driverDto, @PathVariable Integer driverId)
	{
		DriverDto updatedDriver = this.driverService.updateDriver(driverDto, driverId);
		return new ApiResponse<DriverDto>(updatedDriver, "driver updated successfully", true, 200);
		
	}
	
	//get all drivers
	@GetMapping("/getAllDrivers")
	public ApiResponse<Driver> getAllDrivers()
	{
		List<Driver> allDrivers = this.driverService.getAllDrivers();
		return new ApiResponse<>(allDrivers, "drivers found", true, 200);
		
	}
	
	// get driver details by id
	@GetMapping("/drivers/{driverId}")
	public ApiResponse<DriverDto> getDriverById(@PathVariable Integer driverId)
	{
		DriverDto driver = this.driverService.getDriverById(driverId);
		return new ApiResponse<DriverDto>(driver, "driver found with driver id = "+driverId, true, 201);
		
	}
	
	//get drivers by user
	@GetMapping("/getDriversByUser/{userId}")
	public ApiResponse<List<Driver>> getDriversByUser(@PathVariable Integer userId)
	{
		List<Driver> drivers = this.driverService.getDriversByUser(userId);
		return new ApiResponse<>(drivers, "drivers found by user with user Id = "+userId, true, 200);
		
		
		
	}
//	@GetMapping("/getVehiclesByUser/{userId}")
//	public ApiResponse<List<Vehicle>> getVehiclesByUser(@PathVariable Integer userId)
//	{
//		List<Vehicle> vehicles = this.vehicleService.getVehiclesByUser(userId);
//		
//		
//	}

	
	// upload driver image
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/uploadDriverImage/{driverId}")
	public ApiResponse<DriverDto> uploadDriverImage(@RequestParam("image") MultipartFile image,@PathVariable Integer driverId)throws IOException
	{
		DriverDto driverDto = this.driverService.getDriverById(driverId);
		String fileName = this.fileService.uploadImage(path, image);
		
		driverDto.setDriverImage(fileName);
		DriverDto updatedDriver = this.driverService.updateDriver(driverDto, driverId);
		return new ApiResponse<DriverDto>(updatedDriver, "driver image added successfully", true, 200);
	}
	
	//upload driver fitness certificate image
	@PostMapping("/uploadDriverFitnessCertificateImage/{driverId}")
	public ApiResponse<DriverDto> uploadDriverFitnessCertificateImage(@RequestParam("image") MultipartFile image, @PathVariable Integer driverId) throws IOException
	{
		DriverDto driverDto = this.driverService.getDriverById(driverId);
		String fileName = this.fileService.uploadImage(path, image);
		
		driverDto.setDriverFitnessCertificateImage(fileName);
		DriverDto updatedDriver = this.driverService.updateDriver(driverDto, driverId);
		
		return new ApiResponse<DriverDto>(updatedDriver, "driver fitness certificate image added successfully", true, 200);
		
	}
	
	//upload driver agreement image
	@PostMapping("/uploadDriverAgreementImage/{driverId}")
	public ApiResponse<DriverDto> uploadDriverAgreementImage(@RequestParam("image") MultipartFile image,@PathVariable Integer driverId) throws IOException
	{
		DriverDto driverDto = this.driverService.getDriverById(driverId);
		String fileName = this.fileService.uploadImage(path, image);
		
		driverDto.setDriverAgreementImage(fileName);
		DriverDto updatedDriver = this.driverService.updateDriver(driverDto, driverId);
		return new ApiResponse<DriverDto>(updatedDriver, "driver agreement uploaded successfully", true, 200);
		
	}
	
	//upload driving license image
	@PostMapping("/uploadDrivingLicenseImage/{driverId}")
	public ApiResponse<DriverDto> uploadDrivingLicenseImage(@RequestParam("image") MultipartFile image, @PathVariable Integer driverId) throws IOException
	{
		DriverDto driverDto = this.driverService.getDriverById(driverId);
		String fileName = this.fileService.uploadImage(path, image);
		
		driverDto.setDrivingLicenseImage(fileName);
		DriverDto updatedDriver = this.driverService.updateDriver(driverDto, driverId);
		return new ApiResponse<DriverDto>(updatedDriver, "driving license uploaded successfully", true, 200);
		
	}
	
	// download image or method to serve file
	@GetMapping(value="driver/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadDriverImage(@PathVariable("imageName") String imageName, HttpServletResponse response)throws IOException
	{
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	// get drivers by city and category
	@GetMapping("/getDriversByCityAndCategory/city/{d_city}/category/{d_categoryId}")
	public ApiResponse<List<DriverDto>> getDriversByCityAndCategory(@PathVariable String d_city, @PathVariable Integer d_categoryId)
	{
//		List<VehicleDto> vehicleDtos = this.vehicleService.getVehiclesByCityandCategory(city, categoryId);
		List<DriverDto> driverDtos = this.driverService.getDriversByCityAndCategory(d_city,d_categoryId);
		return new ApiResponse<>(driverDtos, "drivers found", true, 200);
		
	}
	
	//get drivers by city and category with pagination
	@GetMapping("/getAllDriversByCityAndCategoryWithPagination/city/{d_city}/category/{d_categoryId}")
	public ResponseEntity<DriverResponse> getAllDriversByCityAndCategoryWithPagination(
			@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
			@PathVariable("d_city") String d_city,
			@PathVariable Integer d_categoryId
			)
	{
		DriverResponse driverResponse = this.driverService.getAllDriversByCityAndCategoryWithPagination(pageNumber,pageSize,sortBy,sortDir,d_city,d_categoryId);
		return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.OK);
		
	}
//	@GetMapping("/getAllVehiclesByCityAndCategoryWithPagination/city/{city}/category/{categoryId}")
//	public ResponseEntity<VehicleResponse> getAllVehiclesByCityAndCategoryWithPagination(
//			@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
//			@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
//			@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
//			@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
//			@PathVariable("city") String city,
//			@PathVariable Integer categoryId				
//			)
//	{
//		VehicleResponse vehicleResponse = this.vehicleService.getAllVehiclesByCityAndCategoryWithPagination(pageNumber,pageSize,sortBy,sortDir,city,categoryId);
//		return new ResponseEntity<VehicleResponse>(vehicleResponse, HttpStatus.OK);
//					
//	}
	
	// get no of drivers added by user
	@GetMapping("/getNoOfDriversAddedByUser/{userId}")
	public ApiResponse<Integer> getNoOfDriversAddedByUser(@PathVariable Integer userId)
	{
		Integer drivers = this.driverService.getNoOfDriversAddedByUser(userId);
		return new ApiResponse<Integer>(drivers, drivers + " are added by user with id = " + userId, true, 200);
		
	}
	
	// get all drivers added by user with pagination
	@GetMapping("/getAllDriversAddedByUserWithPagination/{userId}")
	public ResponseEntity<DriverResponse> getAllDriversAddedByUserWithPagination(
			@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
			@PathVariable Integer userId
			)
	{
		DriverResponse driverResponse = this.driverService.getAllDriversAddedByUserWithPagination(pageNumber,pageSize,sortBy,sortDir,userId);
		return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.OK);
		
		
	}
	

	

}
