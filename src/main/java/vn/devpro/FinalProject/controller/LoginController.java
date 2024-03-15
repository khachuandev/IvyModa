package vn.devpro.FinalProject.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.model.Role;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.RoleService;
import vn.devpro.FinalProject.service.UserService;

@Controller
public class LoginController extends BaseController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws IOException {
		return "login-signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() throws IOException {
		return "login-signup";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(new BCryptPasswordEncoder(4).encode(request.getParameter("password")));
		user.setMobile(request.getParameter("mobile"));
		// Set role cho user moi - mac dinh role la 'GUEST'
		// + lay role co name la 'GUEST' trong DB
		Role role = roleService.getRoleByName("guest");
		user.addRelationalUserRole(role);
		userService.saveOrUpdate(user);
		return "redirect:/login";
	}
}
