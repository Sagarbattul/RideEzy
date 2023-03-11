package com.mycode.blog.payloads;

import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDto {

	private int id;
	
	private String model;
	
	private String number;
	
	private String city;
	
	private String seatingCapacity;
	
	private String luggageCapacity;
	
	private String fuelType;
	
	private String transmission;
	
	private String airCondition;
	
	private String mileage;
	
	private User user;
	
	private Category category;
	
	private String vehicleImage;
	
	private String vehicleRCImage;
	
	private String vehiclePUCImage;
	
	private String vehicleInsuranceImage;
	
	private String vehicleAgreementImage;
}
