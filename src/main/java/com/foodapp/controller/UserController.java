package com.foodapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodapp.entity.Role;
import com.foodapp.entity.User;
import com.foodapp.service.AdminService;
import com.foodapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		User user = userService.login(email, password);
		if (user == null) {
			model.addAttribute("message", "Incorrect username or password");
			return "login";
		}
		session.setAttribute("user", user);

		// ✅ Use consistent session attribute name
		session.setAttribute("loggedInUser", user);

		switch (user.getRole()) {
		case ADMIN:
			return "redirect:/admin";
		case WAITER:
			return "redirect:/waiter";
		case CHEF:
			return "redirect:/chef";
		default:
			model.addAttribute("message", "Invalid role");
			return "login";
		}
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user, @RequestParam String registrationToken, Model model,
			@RequestParam String role) {
		if (!adminService.validateToken(registrationToken)) {
			model.addAttribute("message", "Invalid or expired token.");
			return "register";
		}
		String roleFromToken = adminService.getRoleFromToken(registrationToken);

		if (!role.equals(roleFromToken)) {
			model.addAttribute("message", "selected role does not matched the token assigned role");
			return "register";
		}
		/*
		 * if (role.equals("ADMIN")) { model.addAttribute("message",
		 * "Self-registration as Admin is not allowed."); return "register"; }
		 */

		user.setRole(Role.valueOf(role));

		if (userService.registerUser(user, registrationToken)) {
			model.addAttribute("message", "Registration successful.");
			return "login";
		} else {
			model.addAttribute("message", "Email already exists or registration failed.");
			return "register";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		session.invalidate();
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store, no-cache, must-revalidate");
		response.setHeader(HttpHeaders.PRAGMA, "no-cache");
		response.setHeader(HttpHeaders.EXPIRES, "0");

		model.addAttribute("message", "Logged out successfully.");
		return "login";

	}
}