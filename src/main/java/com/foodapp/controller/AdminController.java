package com.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodapp.entity.BillAmount;
import com.foodapp.entity.MenuItem;
import com.foodapp.entity.Role;
import com.foodapp.entity.User;
import com.foodapp.service.AdminService;
import com.foodapp.service.BillAmountService;
import com.foodapp.service.RestaurantTableService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private RestaurantTableService tableService;

	@Autowired
	private BillAmountService billService;

	private boolean isNotAdmin(User user) {
		return user == null || user.getRole() != Role.ADMIN;
	}

	private String prepareDashboard(HttpSession session, Model model, String section) {
		User user = (User) session.getAttribute("loggedInUser");
		if (isNotAdmin(user)) {
			model.addAttribute("message", "Access denied");
			return "login";
		}
		model.addAttribute("admin", user);
		model.addAttribute("tables", tableService.getAllTables());
		model.addAttribute("users", adminService.getAllUsersExcludingAdmins());
		model.addAttribute("section", section);
		return "admin_dashboard";
	}

	@GetMapping
	public String dashboard(HttpSession session, Model model) {
		return prepareDashboard(session, model, "home");
	}

	@PostMapping("/addTable")
	public String addTable(@RequestParam int tableNumber, HttpSession session, Model model) {
		try {
			tableService.addTable(tableNumber);
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
		}
		return prepareDashboard(session, model, "home");
	}

	@PostMapping("/generate-token")
	public String generateToken(@RequestParam String role, HttpSession session, Model model) {
		String token = adminService.generateToken(role);
		model.addAttribute("generatedToken", token);
		model.addAttribute("role", role);
		return prepareDashboard(session, model, "home");
	}

	@GetMapping("/bills")
	public String viewBills(@RequestParam(required = false) String waiterName, HttpSession session, Model model) {
		List<BillAmount> bills = (waiterName != null && !waiterName.isEmpty())
				? billService.getBillsByWaiter(waiterName)
				: billService.getAllBills();
		model.addAttribute("bills", bills);
		model.addAttribute("totalAmount", bills.stream().mapToDouble(BillAmount::getAmount).sum());
		model.addAttribute("filterApplied", waiterName != null && !waiterName.isEmpty());
		model.addAttribute("waiterName", waiterName);
		return prepareDashboard(session, model, "bills");
	}

	@GetMapping("/list")
	public String listUsers(HttpSession session, Model model) {
		model.addAttribute("users", adminService.findAll());
		return prepareDashboard(session, model, "list");
	}
	@GetMapping("/addMenu")
	public String showAddMenuForm(HttpSession session,Model model) {
	    model.addAttribute("menuItem", adminService.findAllMenu());
		return prepareDashboard(session, model, "addMenu");
	}
	@PostMapping("/addMenu")
	public String addMenu(@ModelAttribute MenuItem menuItem, Model model,HttpSession session) {
	    adminService.addItem(menuItem);
	    model.addAttribute("message", "Menu Item added successfully!");
	    model.addAttribute("item", menuItem);
	    return "redirect:/admin/addMenu";  
	}



}
