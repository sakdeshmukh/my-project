package com.cdac.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int cartId;
	//private long serviceId;
	private double basePrice;
	private double totalAmount;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<CartItem> cartItems = new ArrayList<>();

	@OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
	
	// No-argument constructor
	public Cart() {
		super();
	}
	
	public Cart(double basePrice, double totalAmount) {
		super();
		this.basePrice = basePrice;
		this.totalAmount = totalAmount;
	}
	
	public void addCartItem(CartItem cartItem)
	{
		this.cartItems.add(cartItem);
	}
	public void removeCartItem(CartItem cartItem)
	{
		this.cartItems.remove(cartItem);
	}
	

}
