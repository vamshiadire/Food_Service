package com.foodapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foodapp.entity.CartItem;
import com.foodapp.entity.MenuItem;

import com.foodapp.entity.RestaurantTable;
import com.foodapp.entity.Role;
import com.foodapp.entity.User;
import com.foodapp.service.AdminService;
import com.foodapp.service.RestaurantTableService;
import com.foodapp.service.WaiterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/waiter")
public class WaiterController {

	@Autowired
	private WaiterService waiterService;

	@Autowired
	private RestaurantTableService restaurantTableService;

	@Autowired
	private AdminService adminService; // Add MenuItemService for fetching food items

	@GetMapping
	public String waiterDashboard(@RequestParam(value = "releaseSuccess", required = false) String releaseSuccess,
			Model model, HttpSession session) {
		List<RestaurantTable> tables = waiterService.getAllTables();
		model.addAttribute("tables", tables);

		User loggedInUser = (User) session.getAttribute("loggedInUser");
		model.addAttribute("loggedInUser", loggedInUser);

		if ("true".equals(releaseSuccess)) {
			model.addAttribute("message", "Table released successfully.");
		}

		return "waiter_dashboard";
	}

	@PostMapping("/selectTable")
	public String selectTable(@RequestParam("tableId") Long tableId, Model model, HttpSession session) {
		RestaurantTable table = waiterService.getTableById(tableId);
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		if (loggedInUser == null) {
			model.addAttribute("error", "You must be logged in to book a table.");
			model.addAttribute("tables", waiterService.getAllTables());
			return "waiter_dashboard";
		}

		if (table == null) {
			model.addAttribute("error", "Invalid table.");
			model.addAttribute("tables", waiterService.getAllTables());
			return "waiter_dashboard";
		}

		if (table.isStatus()) {
			model.addAttribute("error", "Table is already booked.");
			model.addAttribute("tables", waiterService.getAllTables());
			return "waiter_dashboard";
		}

		waiterService.bookTable(tableId, loggedInUser);
		return "redirect:/waiter/showFoodMenu?tableId=" + table.getTableNumber();
	}

	@PostMapping("/continueOrder")
	public String continueOrder(@RequestParam("tableId") Long tableId, HttpSession session,
			RedirectAttributes redirectAttrs) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user == null) {
			redirectAttrs.addFlashAttribute("error", "Please login first");
			return "redirect:/login";
		}
		String waiterName = user.getUsername();

		try {
			RestaurantTable servingTable = restaurantTableService.continueOrder(tableId, waiterName);
			session.setAttribute("servingTable", servingTable);
			return "redirect:/waiter/showFoodMenu?tableId=" + tableId; // Pass tableId here
		} catch (RuntimeException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/waiter";
		}
	}

	@PostMapping("/releaseTable")
	public String releaseTable(@RequestParam Long tableId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		RestaurantTable table = waiterService.getTableById(tableId);

		if (loggedInUser != null && loggedInUser.getRole() == Role.WAITER
				&& table.getAssignedWaiter().getId() == loggedInUser.getId()) {
			waiterService.releaseTable(tableId, loggedInUser);
		} else {
			return "redirect:/unauthorized";
		}

		return "redirect:/waiter?releaseSuccess=true";
	}

	@GetMapping("/showFoodMenu")
	public String showFoodMenu(@RequestParam("tableId") int tableNumber, HttpSession session, Model model) {
		RestaurantTable table = waiterService.getTableByTableNumber(tableNumber);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<MenuItem> menuItems = adminService.findAllMenu();

		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		model.addAttribute("table", table);
		model.addAttribute("loggedInUser", loggedInUser);
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("cart", cart);

		return "food_menu";
	}

	@GetMapping("/addToCart")
	public String addToCart(@RequestParam Long id, HttpSession session, @RequestParam int tableId) {
		Optional<MenuItem> itemOpt = waiterService.getItemById(id);
		if (!itemOpt.isPresent()) {
			return "redirect:/waiter/showFoodMenu?tableId=" + tableId;
		}

		MenuItem menuItem = itemOpt.get();

		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		Optional<CartItem> existingItem = cart.stream().filter(i -> i.getMenuItemId().equals(menuItem.getId()))
				.findFirst();

		if (existingItem.isPresent()) {
			existingItem.get().incrementQuantity();
		} else {
			cart.add(new CartItem(menuItem.getId(), menuItem.getItemName(), menuItem.getPrice()));
		}

		session.setAttribute("cart", cart);
		return "redirect:/waiter/showFoodMenu?tableId=" + tableId;
	}

}
