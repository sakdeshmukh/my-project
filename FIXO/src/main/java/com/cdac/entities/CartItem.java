package com.cdac.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "cart_items")
@NoArgsConstructor
public class CartItem extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    
    private int quantity;
    private double price;
    
    public CartItem(Cart cart, Service service, int quantity, double price) {
        this.cart = cart;
        this.service = service;
        this.quantity = quantity;
        this.price = price;
    }
} 