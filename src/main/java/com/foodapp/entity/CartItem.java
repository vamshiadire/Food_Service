package com.foodapp.entity;

import lombok.Data;

@Data
public class CartItem {
    private Long menuItemId;
    private String itemName;
    private float price;
    private int quantity;

    public CartItem(Long menuItemId, String itemName, float price) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = 1;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

	public Long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
