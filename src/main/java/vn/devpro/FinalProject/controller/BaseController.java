package vn.devpro.FinalProject.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.devpro.FinalProject.dto.Cart;
import vn.devpro.FinalProject.model.User;

@Configuration
public class BaseController {
	
	@ModelAttribute("title")
	public String projectTitle() {
		return "FinalProject";
	}
	
	@ModelAttribute("totalCartProducts")
	public BigInteger getTotalCartProducts(final HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") == null) {
			return BigInteger.ZERO;
		}
		
		Cart cart = (Cart)session.getAttribute("cart");
		return cart.totalCartProduct();
	}
	
	@ModelAttribute("loginedUser") 
	public User getLoginedUser() {
		Object loginedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(loginedUser != null && loginedUser instanceof UserDetails) {
			User user = (User) loginedUser;
			return user;
		}
		return new User();
	}
	
	// Kiem tra da login hay chua?
	@ModelAttribute("isLogined") 
	public boolean isLogined() {
		Object loginedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(loginedUser != null && loginedUser instanceof UserDetails) {
			User user = (User) loginedUser;
			return true;
		}
		return false;
	}
}
