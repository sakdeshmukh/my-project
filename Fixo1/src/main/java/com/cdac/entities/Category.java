package com.cdac.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	
	//1 category --> many services
	@OneToMany (mappedBy = "myCategory", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<Service> services = new ArrayList<>();

	public Category(String categoryName, List<Service> services) {
		super();
		this.categoryName = categoryName;
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
