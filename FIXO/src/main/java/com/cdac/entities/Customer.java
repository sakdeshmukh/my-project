package com.cdac.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Table(name = "customers")
@Entity
public class Customer extends BaseEntity {

	@Column(length = 30, name = "first_name")
	private String firstName;
	@Column(length = 30, name = "last_name")
	private String lastName;
//	@Column(length = 30, unique = true)
//	private String email;
	
	@Column(length = 50, nullable = false)
	private String address;
//	@Column(length = 30, nullable = false)
//	private String password;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	
	// Manual getters and setters for Lombok compatibility
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
