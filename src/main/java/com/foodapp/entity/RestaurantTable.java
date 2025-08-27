package com.foodapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    @Column(nullable = false, unique = true)
    private int tableNumber;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private User assignedWaiter;

	public User getAssignedWaiter() {
		return assignedWaiter;
	}

	public void setAssignedWaiter(User assignedWaiter) {
		this.assignedWaiter = assignedWaiter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	@Override
	public String toString() {
		return "RestaurantTable{" + "id=" + id + ", tableNumber=" + tableNumber + ", status=" + status + '}';
	}
    
}
