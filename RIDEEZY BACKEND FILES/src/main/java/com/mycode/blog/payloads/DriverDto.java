package com.mycode.blog.payloads;

import javax.persistence.Column;

import com.mycode.blog.entities.Category;
import com.mycode.blog.entities.DriverCategory;
import com.mycode.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DriverDto {

	private int d_id;
	
	private String d_bloodGroup;
	
	private String d_altMobNo;
	
	private String d_ridingExperience;
	
	private String d_ratings;
	
	private String d_education;
	
	private String d_knownLanguages;
	
	private User user;
	
	private DriverCategory driverCategory;
	
		
	private String d_firstName;
	
	
	private String d_lastName;
	
	
	private String d_address;
	
	
	private String d_city;
	
	
	private String d_dob;
	
	private String d_gender;
	
	private String driverImage;
	
	private String driverFitnessCertificateImage;
	
	private String driverAgreementImage;
	
	private String drivingLicenseImage;
	
	private String about;
}
