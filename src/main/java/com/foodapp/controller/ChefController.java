package com.foodapp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodapp.entity.User;
import com.foodapp.service.ChefService;

import jakarta.servlet.http.HttpSession;
import com.foodapp.entity.Order;
import com.foodapp.service.WaiterService;

@Controller
@RequestMapping("/chef")
public class ChefController {
	

	@Autowired
	private ChefService chefService;

	@Autowired
	private WaiterService waiterService;

	@GetMapping("")
	public String chefDashboard(Model model) {
		List<Order> orders = waiterService.getAllOrders();
		model.addAttribute("orders", orders);
		return "chef_dashboard";
	}

	@GetMapping("/orders/{id}")
	public String showOrder(@PathVariable Long id, Model model) {
		Order order = chefService.getOrderById(id); 
		model.addAttribute("order", order);
		return "order_detail";
	}

	@PostMapping("/claimOrder")
	public String claim(@RequestParam Long orderId, HttpSession s) {
		User chef = (User) s.getAttribute("loggedInChef");
		System.out.println(chef);
		if (chef != null)
			chefService.claimOrder(orderId, chef);
		return "redirect:/chef/orders/" + orderId;

	}

	@PostMapping("/updateStatus")
	public String update(@RequestParam Long orderId, @RequestParam String status) {
		chefService.updateOrderStatus(orderId, status);
		return "redirect:/chef/orders/" + orderId;

	}
}
