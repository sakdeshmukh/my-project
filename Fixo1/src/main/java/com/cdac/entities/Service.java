package com.cdac.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString (exclude = {"myCategory" , "serviceProvider"})
@Entity
@Table (name = "services" ,uniqueConstraints = @UniqueConstraint( columnNames = {"service_name", "base_price", "description"}))
@NoArgsConstructor
public class Service extends BaseEntity {

	//private long serviceId;
	//private long categoryId;
	@Column(length = 30 , name = "service_name")
	private String serviceName;
	@Column(name = "base_price")
	private double basePrice;
	@Column(length = 50)
	private String description;
	
	//many service ---> 1 category
	@ManyToOne
	@JoinColumn(name = "categoryId" , nullable = false)
	private Category myCategory;
	
	//one service-->many serviceProvider
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceProvider> serviceProvider = new ArrayList<>();
	

	

	public Service( String serviceName, double basePrice, String description, Category myCategory) {
		super();
		//this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.basePrice = basePrice;
		this.description = description;
		this.myCategory = myCategory;
	}
	
	// Manual getters and setters for Lombok compatibility
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category getMyCategory() {
		return myCategory;
	}
	
	public void setMyCategory(Category myCategory) {
		this.myCategory = myCategory;
	}
	
	public List<ServiceProvider> getServiceProvider() {
		return serviceProvider;
	}
	
	public void setServiceProvider(List<ServiceProvider> serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
}
