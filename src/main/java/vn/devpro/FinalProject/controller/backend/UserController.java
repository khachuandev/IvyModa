package vn.devpro.FinalProject.controller.backend;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.controller.BaseController;
import vn.devpro.FinalProject.model.Role;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.RoleService;
import vn.devpro.FinalProject.service.UserService;

@Controller
@RequestMapping("/admin/user/")
public class UserController extends BaseController {
    @Autowired 
    private UserService userService;
    
    @Autowired 
    private RoleService roleService;
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "backend/user-list";
    }
    
    @RequestMapping(value = "add", method = RequestMethod.GET) 
    public String add(final Model model) {
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        
        User user = new User();
        user.setCreateDate(new Date());
        model.addAttribute("user", user);
        return "backend/user-add";
    }
    
    @RequestMapping(value = "add-save", method = RequestMethod.POST)
    public String addSave(final Model model, @ModelAttribute("user") User user) {
    	user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
        userService.saveOrUpdate(user);
        return "redirect:/admin/user/add";
    }
    
    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String edit(final Model model, @PathVariable("userId") int userId) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        User user = userService.getById(userId);
        model.addAttribute("user", user);
        return "backend/user-edit";
    }
    
    @RequestMapping(value = "edit-save", method = RequestMethod.POST)
    public String editSave(final Model model, @ModelAttribute("user") User user) {
        // Kiểm tra xem mật khẩu mới đã được nhập hay không
        if (!user.getPassword().isEmpty()) {
            // Mã hóa mật khẩu mới trước khi lưu vào cơ sở dữ liệu
            user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
        }
        
        // Lưu hoặc cập nhật thông tin người dùng
        userService.saveOrUpdate(user);
        
        // Chuyển hướng về trang danh sách người dùng
        return "redirect:/admin/user/list";
    }
    
    @RequestMapping(value = "delete/{userId}", method = RequestMethod.GET)
    public String delete(final Model model, @PathVariable("userId") int userId) {
        User user = userService.getById(userId);
        user.setStatus(false);
        userService.inactiveUser(user);
        return "redirect:/admin/user/list";
    }
}