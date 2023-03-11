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
import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.Vehicle;
import com.mycode.blog.payloads.DriverDto;
import com.mycode.blog.payloads.VehicleDto;
import com.mycode.blog.payloads.VehicleResponse;
import com.mycode.blog.services.FileService;
import com.mycode.blog.services.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private FileService fileService;
	
	
	//post add new vehicle ()
//	@PostMapping("/user/{userId}/category/{categoryId}/addVehicle")
//	public ApiResponse<VehicleDto> addVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Integer userId, @PathVariable Integer categoryId ){
//		
//		//this.vehicleService.addNewVehicle(vehicle);
//		VehicleDto addVehicle = this.vehicleService.addVehicle(vehicleDto, userId, categoryId);
//		return new ApiResponse(addVehicle,"Vehicle registered successfully.",true,201);
//	}
	//below code will add a vehicle just response format changed recent is above
	@PostMapping("/user/{userId}/category/{categoryId}/addVehicle")
	public ResponseEntity<VehicleDto> addVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Integer userId, @PathVariable Integer categoryId)
	{
		VehicleDto addVehicle = this.vehicleService.addVehicle(vehicleDto, userId, categoryId);
		
		return new ResponseEntity<VehicleDto>(addVehicle, HttpStatus.CREATED);
		
	}

	
	//get vehicle detail by id
		@GetMapping("/vehicles/{vehicleId}")
		public ApiResponse<VehicleDto> getVehicleById(@PathVariable Integer vehicleId)
		{
				//PostDto post = this.postService.getPostById(postId);
				VehicleDto vehicle = this.vehicleService.getVehicleById(vehicleId);
				return new ApiResponse(vehicle,"Vehicle found with vehicleId =" +vehicleId,true,201);
		}
		
		
		//delete vehicle by id
		@DeleteMapping("/deleteVehicle/{vehicleId}")
		public ApiResponse<Vehicle> deleteVehicle(@PathVariable Integer vehicleId)
		{
			
			this.vehicleService.deleteVehicle(vehicleId);
			//Vehicle vehicle = this.vehicleService.getVehicleById(vehicleId);
			
			
			return new ApiResponse("Vehicle with " + vehicleId + " deleted successfully.",true,200);
		}
		
		// get vehicle by category
		@GetMapping("/getVehiclesByCategory/{categoryId}")
		public ApiResponse<List<Vehicle>> getVehiclesByCategory(@PathVariable Integer categoryId){
			
			List<Vehicle> vehicles = this.vehicleService.getVehiclesByCategory(categoryId);
			return new ApiResponse<>(vehicles, "vehicles found with category " + categoryId, true, 200);
			
		}
		
		//get all vehicles
//		@GetMapping("/all")
//		public ApiResponse<Vehicle> getAllVehicles()
//		{
//			List<Vehicle> allVehicles = this.vehicleService.getAllVehicles();
//			return new ApiResponse<Vehicle>(allVehicles, "Vehicles Found", true, 200);
//			
//		}
		
		@GetMapping("/all")
		public ResponseEntity<List<VehicleDto>> getAllVehicles()
		{
			List<VehicleDto> allVehicles = this.vehicleService.getAllVehicles();
			return new ResponseEntity<List<VehicleDto>>(allVehicles, HttpStatus.OK);
			
		}

		// get vehicles by user
		@GetMapping("/getVehiclesByUser/{userId}")
		public ApiResponse<List<Vehicle>> getVehiclesByUser(@PathVariable Integer userId)
		{
			List<Vehicle> vehicles = this.vehicleService.getVehiclesByUser(userId);
			return new ApiResponse<List<Vehicle>>(vehicles, "vehicles found by user with user id = " + userId, true, 200);
			
		}
		
		//get vehicles with pagination
//		@GetMapping("/getAllVehiclesWithPagination")
//		public ApiResponse<VehicleResponse> getAllVehiclesWithPagination(
//				@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
//				@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
//				@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
//				@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
//				)
//		{
//			VehicleResponse vehicleResponse = this.vehicleService.getAllVehiclesWithPagination(pageNumber, pageSize, sortBy, sortDir);
//			return new ApiResponse(vehicleResponse, "vehicles found", true, 200);
//			
//			
//		}
		@GetMapping("/getAllVehiclesWithPagination")
		public ResponseEntity<VehicleResponse> getAllVehiclesWithPagination(
				@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
				@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
				@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
				@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
				)
		{
			VehicleResponse vehicleResponse = this.vehicleService.getAllVehiclesWithPagination(pageNumber,pageSize,sortBy,sortDir);
			return new ResponseEntity<VehicleResponse>(vehicleResponse, HttpStatus.OK);
			
		}
		
		//get vehicles by city and category with pagination
		@GetMapping("/getAllVehiclesByCityAndCategoryWithPagination/city/{city}/category/{categoryId}")
		public ResponseEntity<VehicleResponse> getAllVehiclesByCityAndCategoryWithPagination(
				@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
				@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
				@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
				@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
				@PathVariable("city") String city,
				@PathVariable Integer categoryId				
				)
		{
			VehicleResponse vehicleResponse = this.vehicleService.getAllVehiclesByCityAndCategoryWithPagination(pageNumber,pageSize,sortBy,sortDir,city,categoryId);
			return new ResponseEntity<VehicleResponse>(vehicleResponse, HttpStatus.OK);
						
		}
		
		//update vehicle
		@PutMapping("/updateVehicle/{vehicleId}")
		public ApiResponse<VehicleDto> updateVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Integer vehicleId)
		{
			VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, vehicleId);
			return new ApiResponse<VehicleDto>(updatedVehicle, "vehicle updated successfully", true, 200);
			
		}
		
		//search vehicle by name
		@GetMapping("/searchVehiclesByName/{keywords}")
		public ApiResponse<List<Vehicle>> searchVehiclesByName(@PathVariable("keywords") String keywords)
		{
			List<Vehicle> result = this.vehicleService.searchVehiclesByName(keywords);
			return new ApiResponse<List<Vehicle>>(result, "vehicles found with given keywords", true, 200);
			
		}
		
		
		// get vehicle by city
		@GetMapping("/searchVehicleByCity/{city}")
		public ApiResponse<List<VehicleDto>> searchVehicleByCity(@PathVariable("city") String city)
		{
			List<VehicleDto> result = this.vehicleService.searchVehicleByCity(city);
			return new ApiResponse<List<VehicleDto>>(result, "vehicles found with giver city", true, 200);
			
		}
		
		@Value("${project.image}")
		private String path;
		
		//vehicle image upload
		@PostMapping("/uploadVehicleImage/{vehicleId}")
		public ApiResponse<VehicleDto> uploadVehicleImage(@RequestParam("image") MultipartFile image, @PathVariable Integer vehicleId)throws IOException
		{
			VehicleDto vehicleDto = this.vehicleService.getVehicleById(vehicleId);
			String fileName = this.fileService.uploadImage(path, image);
			
			vehicleDto.setVehicleImage(fileName);
			VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, vehicleId);
			
			return new ApiResponse(updatedVehicle, "vehicle image added successfully", true, 200);
			
		}
		
		//method to serve files
		@GetMapping(value="vehicle/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
		public void downloadVehicleImage(@PathVariable("imageName") String imageName, HttpServletResponse response)throws IOException
		{
			InputStream resource = this.fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());
		}
		
		//upload vehicle RC Image
		@PostMapping("/uploadVehicleRCImage/{vehicleId}")
		public ApiResponse<VehicleDto> uploadVehicleRCImage(@RequestParam("image") MultipartFile image, @PathVariable Integer vehicleId)throws IOException
		{
			VehicleDto vehicleDto = this.vehicleService.getVehicleById(vehicleId);
			String fileName = this.fileService.uploadImage(path, image);
			
			vehicleDto.setVehicleRCImage(fileName);
			VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, vehicleId);
			return new ApiResponse(updatedVehicle, "vehicle rc image added successfully", true, 200);
			
		}
		
		// upload vehicle PUC Image
		@PostMapping("/uploadVehiclePUCImage/{vehicleId}")
		public ApiResponse<VehicleDto> uploadVehiclePUCImage(@RequestParam("image") MultipartFile image, @PathVariable Integer vehicleId) throws IOException
		{
			VehicleDto vehicleDto = this.vehicleService.getVehicleById(vehicleId);
			String fileName = this.fileService.uploadImage(path, image);
			
			vehicleDto.setVehiclePUCImage(fileName);
			VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, vehicleId);
			return new ApiResponse(updatedVehicle, "vehicle puc image added successfully", true, 200);
			
		}
		
		//upload vehicle insurance image
		@PostMapping("/uploadVehicleInsuranceImage/{vehicleId}")
		public ApiResponse<VehicleDto> uploadVehicleInsuranceImage(@RequestParam("image") MultipartFile image, @PathVariable Integer vehicleId) throws IOException
		{
			VehicleDto vehicleDto = this.vehicleService.getVehicleById(vehicleId);
			String fileName = this.fileService.uploadImage(path, image);
			
			vehicleDto.setVehicleInsuranceImage(fileName);
			VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, vehicleId);
			return new ApiResponse(updatedVehicle, "vehicle puc image added successfully", true, 200);
			
		}
		
		//upload vehicle agreement image
		@PostMapping("/uploadVehicleAgreementImage/{vehicleId}")
		public ApiResponse<VehicleDto> uploadVehicleAgreementImage(@RequestParam("image") MultipartFile image, @PathVariable Integer vehicleId) throws IOException
		{
			VehicleDto vehicleDto = this.vehicleService.getVehicleById(vehicleId);
			String fileName = this.fileService.uploadImage(path, image);
			
			vehicleDto.setVehicleAgreementImage(fileName);
			VehicleDto updatedVehicle = this.vehicleService.updateVehicle(vehicleDto, vehicleId);
			return new ApiResponse(updatedVehicle, "vehicle puc image added successfully", true, 200);
			
		}
		
		
		// get vehicles by city and category
		@GetMapping("/getVehiclesByCityandCategory/city/{city}/category/{categoryId}")
		public ApiResponse<List<VehicleDto>> getVehiclesByCityandCategory(@PathVariable("city") String city, @PathVariable Integer categoryId)
		{
			List<VehicleDto> vehicleDtos = this.vehicleService.getVehiclesByCityandCategory(city, categoryId);
			return new ApiResponse<>(vehicleDtos, "vehicles found", true, 200);
			
		}
		
		
		// get no of vehicles added by user
		@GetMapping("/getNoOfVehiclesAddedByUser/{userId}")
		public ApiResponse<Integer> getNoOfVehiclesAddedByUser(@PathVariable Integer userId)
		{
			int noOfVehicles = this.vehicleService.getNoOfVehiclesAddedByUser(userId);
			return new ApiResponse<Integer>(noOfVehicles, noOfVehicles +" are added by user with id = "+userId, true, 200);
			
		}
		
		// get all vehicles added by user with pagination
		@GetMapping("/getAllVehicleAddedByUserWithPagination/{userId}")
		public ResponseEntity<VehicleResponse> getAllVehicleAddedByUserWithPagination(
				@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
				@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
				@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
				@RequestParam(value="sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
				@PathVariable Integer userId
				)
		{
			
			VehicleResponse vehicleResponse = this.vehicleService.getAllVehicleAddedByUserWithPagination(pageNumber,pageSize,sortBy,sortDir,userId);
			return new ResponseEntity<VehicleResponse>(vehicleResponse, HttpStatus.OK);
			
		}
			

}
