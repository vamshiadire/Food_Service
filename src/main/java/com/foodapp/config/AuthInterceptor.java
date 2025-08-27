package com.foodapp.config;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();

		if (uri.equals("/login") || uri.equals("/register") || uri.equals("/logout") || uri.startsWith("/resources")) {
			return true;
		}

		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("/login");
			return false;
		}

		// Set no-cache headers to prevent back button access after logout
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		return true;
	}
}
