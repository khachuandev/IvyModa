package vn.devpro.FinalProject.controller.backend;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.model.BaseModel;
import vn.devpro.FinalProject.model.Role;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.RoleService;
import vn.devpro.FinalProject.service.UserService;



@Controller
@RequestMapping("/admin/role/")
public class RoleController extends BaseModel {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "backend/role-list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(final Model model) {
    	List<User> users = userService.findAll();
		model.addAttribute("users", users);
        Role role = new Role();
        role.setCreateDate(new Date());
        model.addAttribute("role", role);
        return "backend/role-add";
    }

    @RequestMapping(value = "add-save", method = RequestMethod.POST)
    public String addSave(final Model model, @ModelAttribute("role") Role role) {
        roleService.saveOrUpdate(role);
        return "redirect:/admin/role/add";
    }

    @RequestMapping(value = "edit/{roleId}", method = RequestMethod.GET)
    public String edit(final Model model, @PathVariable("roleId") int roleId) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        Role role = roleService.getById(roleId);
        model.addAttribute("role", role);
        return "backend/role-edit";
    }
    
    @RequestMapping(value = "edit-save", method = RequestMethod.POST)
    public String editSave(final Model model, @ModelAttribute("role") Role role) {
        roleService.saveOrUpdate(role);
        return "redirect:/admin/role/list";
    }
    
    @RequestMapping(value = "delete/{roleId}", method = RequestMethod.GET)
    public String delete(final Model model, @PathVariable("roleId") int roleId) {
        Role role = roleService.getById(roleId);
        role.setStatus(false);
        roleService.inactiveRole(role);
        return "redirect:/admin/role/list";
    }
}
