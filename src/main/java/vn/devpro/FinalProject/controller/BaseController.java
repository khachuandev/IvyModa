package vn.devpro.FinalProject.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.devpro.FinalProject.dto.Cart;

@Configuration
public class BaseController {
	
	@ModelAttribute("title")
	public String projectTitle() {
		return "FinalProject";
	}
	
	/*
	 * @ModelAttribute("totalCartProducts") public BigInteger
	 * getTotalCartProducts(final HttpServletRequest request) { HttpSession session
	 * = request.getSession(); if(session.getAttribute("cart") == null) { return
	 * BigInteger.ZERO; }
	 * 
	 * Cart cart = (Cart)session.getAttribute("cart"); return
	 * cart.totalCartProduct(); }
	 */
}
