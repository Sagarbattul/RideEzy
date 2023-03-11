package com.mycode.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="registered_users2")
@NoArgsConstructor
@Setter
@Getter
public class User implements UserDetails{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="first_name", nullable = false, length=100)
	private String firstName;
	
	@Column(name="last_name", nullable = false, length=100)
	private String lastName;
	
//	@Column(name="date_of_birth", nullable=false)
//	private String dob;
	
	@Column(unique = true)
//	@Email(message ="email must be in proper format")
//	@NotEmpty
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="mobile_no")
	private String mobile;
	
	@Column(name="city")
	private String city;
	
	@Column(name="date_of_birth")
	private String dob;
	
	//@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Vehicle> vehicles = new ArrayList<>();
	
	private String userImage;
	

	@JsonProperty
	public int getId() {
		return id;
	}

	@JsonIgnore
	public void setId(int id) {
		this.id = id;
	}

	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	@JsonIgnore
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	
	


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}

	public User(int id, String firstName, String lastName, String email, String password, String address, String gender,
			String mobile, String city, String dob, List<Vehicle> vehicles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.mobile = mobile;
		this.city = city;
		this.dob = dob;
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", address=" + address + ", gender=" + gender + ", mobile=" + mobile
				+ ", city=" + city + ", dob=" + dob + ", vehicles=" + vehicles + "]";
	}

	


	
	
	
	
}
