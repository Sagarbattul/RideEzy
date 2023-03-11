package com.mycode.blog.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

@Entity
@Table(name="drivers")
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int d_id;
	
	@Column
	private String d_bloodGroup;
	
	@Column
	private String d_altMobNo;
	
	@Column
	private String d_ridingExperience;
	
	@Column
	private String d_ratings;
	
	@Column
	private String d_education;
	
	@Column
	private String d_knownLanguages;
	
	@Column
	private String d_firstName;
	
	@Column
	private String d_lastName;
	
	@Column
	private String d_address;
	
	@Column
	private String d_city;
	
	@Column
	private String d_dob;
	
	@Column
	private String d_gender;
	
	@Column
	private String driverImage;
	
	@Column
	private String driverFitnessCertificateImage;
	
	@Column
	private String driverAgreementImage;
	
	@Column
	private String drivingLicenseImage;
	
	@Column
	private String about;
		
	//@JsonBackReference
	//@Cascade(CascadeType.ALL)
	@ManyToOne	
	@JoinColumn(name="driver_category_id")
	private DriverCategory driverCategory;
	
	@ManyToOne
	private User user;
	
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy = "driver")
	private Set<DriverRating> driverRatings = new HashSet<>();
	
	@JsonIgnore
	public void setDriverRatings(Set<DriverRating> driverRatings) {
		this.driverRatings = driverRatings;
	}
	
	
	

	public Set<DriverRating> getDriverRatings() {
		return driverRatings;
	}
	
	
	public User getUser() {
		return user;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public String getD_bloodGroup() {
		return d_bloodGroup;
	}

	public void setD_bloodGroup(String d_bloodGroup) {
		this.d_bloodGroup = d_bloodGroup;
	}

	public String getD_altMobNo() {
		return d_altMobNo;
	}

	public void setD_altMobNo(String d_altMobNo) {
		this.d_altMobNo = d_altMobNo;
	}

	public String getD_ridingExperience() {
		return d_ridingExperience;
	}

	public void setD_ridingExperience(String d_ridingExperience) {
		this.d_ridingExperience = d_ridingExperience;
	}

	public String getD_ratings() {
		return d_ratings;
	}

	public void setD_ratings(String d_ratings) {
		this.d_ratings = d_ratings;
	}

	public String getD_education() {
		return d_education;
	}

	public void setD_education(String d_education) {
		this.d_education = d_education;
	}

	public String getD_knownLanguages() {
		return d_knownLanguages;
	}

	public void setD_knownLanguages(String d_knownLanguages) {
		this.d_knownLanguages = d_knownLanguages;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public DriverCategory getDriverCategory() {
		return driverCategory;
	}

	public void setDriverCategory(DriverCategory driverCategory) {
		this.driverCategory = driverCategory;
	}

	

	public String getD_firstName() {
		return d_firstName;
	}

	public void setD_firstName(String d_firstName) {
		this.d_firstName = d_firstName;
	}

	public String getD_lastName() {
		return d_lastName;
	}

	public void setD_lastName(String d_lastName) {
		this.d_lastName = d_lastName;
	}

	public String getD_address() {
		return d_address;
	}

	public void setD_address(String d_address) {
		this.d_address = d_address;
	}

	public String getD_city() {
		return d_city;
	}

	public void setD_city(String d_city) {
		this.d_city = d_city;
	}

	public String getD_dob() {
		return d_dob;
	}

	public void setD_dob(String d_dob) {
		this.d_dob = d_dob;
	}

	public String getD_gender() {
		return d_gender;
	}

	public void setD_gender(String d_gender) {
		this.d_gender = d_gender;
	}

	public String getDriverImage() {
		return driverImage;
	}

	public void setDriverImage(String driverImage) {
		this.driverImage = driverImage;
	}
			
	public String getDriverFitnessCertificateImage() {
		return driverFitnessCertificateImage;
	}
	
	public void setDriverFitnessCertificateImage(String driverFitnessCertificateImage) {
		this.driverFitnessCertificateImage = driverFitnessCertificateImage;
	}
	
	public String getDriverAgreementImage() {
		return driverAgreementImage;
	}

	public void setDriverAgreementImage(String driverAgreementImage) {
		this.driverAgreementImage = driverAgreementImage;
	}
	
	public String getDrivingLicenseImage() {
		return drivingLicenseImage;
	}

	public void setDrivingLicenseImage(String drivingLicenseImage) {
		this.drivingLicenseImage = drivingLicenseImage;
	}

	

	
	
	

	public String getAbout() {
		return about;
	}




	public void setAbout(String about) {
		this.about = about;
	}


	


	public Driver(int d_id, String d_bloodGroup, String d_altMobNo, String d_ridingExperience, String d_ratings,
			String d_education, String d_knownLanguages, String d_firstName, String d_lastName, String d_address,
			String d_city, String d_dob, String d_gender, String driverImage, String driverFitnessCertificateImage,
			String driverAgreementImage, String drivingLicenseImage, String about, DriverCategory driverCategory,
			User user, Set<DriverRating> driverRatings) {
		super();
		this.d_id = d_id;
		this.d_bloodGroup = d_bloodGroup;
		this.d_altMobNo = d_altMobNo;
		this.d_ridingExperience = d_ridingExperience;
		this.d_ratings = d_ratings;
		this.d_education = d_education;
		this.d_knownLanguages = d_knownLanguages;
		this.d_firstName = d_firstName;
		this.d_lastName = d_lastName;
		this.d_address = d_address;
		this.d_city = d_city;
		this.d_dob = d_dob;
		this.d_gender = d_gender;
		this.driverImage = driverImage;
		this.driverFitnessCertificateImage = driverFitnessCertificateImage;
		this.driverAgreementImage = driverAgreementImage;
		this.drivingLicenseImage = drivingLicenseImage;
		this.about = about;
		this.driverCategory = driverCategory;
		this.user = user;
		this.driverRatings = driverRatings;
	}


	

	@Override
	public String toString() {
		return "Driver [d_id=" + d_id + ", d_bloodGroup=" + d_bloodGroup + ", d_altMobNo=" + d_altMobNo
				+ ", d_ridingExperience=" + d_ridingExperience + ", d_ratings=" + d_ratings + ", d_education="
				+ d_education + ", d_knownLanguages=" + d_knownLanguages + ", d_firstName=" + d_firstName
				+ ", d_lastName=" + d_lastName + ", d_address=" + d_address + ", d_city=" + d_city + ", d_dob=" + d_dob
				+ ", d_gender=" + d_gender + ", driverImage=" + driverImage + ", driverFitnessCertificateImage="
				+ driverFitnessCertificateImage + ", driverAgreementImage=" + driverAgreementImage
				+ ", drivingLicenseImage=" + drivingLicenseImage + ", about=" + about + ", driverCategory="
				+ driverCategory + ", user=" + user + ", driverRatings=" + driverRatings + "]";
	}




	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
