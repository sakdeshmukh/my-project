package com.cdac.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "categories")
@Getter
@Setter
@ToString (callSuper = true, exclude = "services")
@NoArgsConstructor
public class Category extends BaseEntity {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long categoryId;
	
	@Column(length = 20, name = "category_name")
	private String categoryName;
	
	@Column(length = 100, name = "description")
	private String description;
	
	//1 category --> many services
	@OneToMany (mappedBy = "myCategory", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<Service> services = new ArrayList<>();

	public Category(String categoryName, List<Service> services) {
		super();
		this.categoryName = categoryName;
		this.services = services;
	}
	
	// Manual getters and setters for Lombok compatibility
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Service> getServices() {
		return services;
	}
	
	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	public void addService(Service service)
	{
		this.services.add(service);
		service.setMyCategory(this);
	}
	public void removeService(Service service)
	{
		this.services.remove(service);
		service.setMyCategory(null);
	}

}
