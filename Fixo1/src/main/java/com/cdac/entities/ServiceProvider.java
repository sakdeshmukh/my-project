	package com.cdac.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "service_providers" , 
uniqueConstraints=@UniqueConstraint(columnNames = {"service_provider_name", "rating","is_verified","years_of_experience" }))
@NoArgsConstructor
public class ServiceProvider extends BaseEntity {
		
	@Column(name = "service_provider_name")
	private String serviceProviderName;
	
	@Column(name = "business_address")
	private String businessAddress;
	
	@Column(name = "service_category")
	private String serviceCategory;
	
	@Column(name = "license_number")
	private String licenseNumber;
	
	@Column(name = "business_description")
	private String businessDescription;
	
	private int rating;
	@Column(name = "is_verified")
	private boolean verified;
	@Column(name = "years_of_experience")
	private int yearsOfExp;
		
	//many serviceprovider ---> 1 service
	@ManyToOne
	@JoinColumn(name = "serviceId", nullable = true) // Made nullable for initial registration
	private Service service;
	
	@OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
	
	// Manual getters and setters for Lombok compatibility
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	
	public String getBusinessAddress() {
		return businessAddress;
	}
	
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	
	public String getServiceCategory() {
		return serviceCategory;
	}
	
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	
	public String getLicenseNumber() {
		return licenseNumber;
	}
	
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	public String getBusinessDescription() {
		return businessDescription;
	}
	
	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public boolean isVerified() {
		return verified;
	}
	
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public int getYearsOfExp() {
		return yearsOfExp;
	}
	
	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}
	
	public com.cdac.entities.Service getService() {
		return service;
	}
	
	public void setService(com.cdac.entities.Service service) {
		this.service = service;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
