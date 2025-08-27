package com.foodapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id") // Foreign key to Order
	private Order order;
	private String itemName;
	private float price;
	private int quantity;

	public void CartItem(String itemName, float price) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = 1;
	}
	// Custom constructor for conversion from CartItem
		public OrderItem(Order order, String itemName, float price, int quantity) {
			this.order = order;
			this.itemName = itemName;
			this.price = price;
			this.quantity = quantity;
		}

	public OrderItem() {
			// TODO Auto-generated constructor stub
		}
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}