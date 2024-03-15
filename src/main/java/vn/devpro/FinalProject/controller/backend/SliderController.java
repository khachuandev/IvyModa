package vn.devpro.FinalProject.controller.backend;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.FinalProject.controller.BaseController;
import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.model.Slider;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.SliderService;
import vn.devpro.FinalProject.service.UserService;

@Controller
@RequestMapping("/admin/slider/")
public class SliderController extends BaseController implements FinalProjectConstant {
	
	@Autowired
	private SliderService sliderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model) {
		List<Slider> sliders = sliderService.findAll();
		model.addAttribute("sliders", sliders);
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "backend/slider-list";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		// Lay danh sach user
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Slider slider = new Slider();
		slider.setCreateDate(new Date());
		model.addAttribute("slider", slider);
		
	    return "backend/slider-add";
	}
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(final Model model,
			@ModelAttribute("slider") Slider slider,
			@RequestParam("imageSlider") MultipartFile imageSlider) throws IOException {
		sliderService.saveAddSlider(slider, imageSlider);
		return "redirect:/admin/slider/list";
	}
	
	@RequestMapping(value = "edit/{sliderId}", method = RequestMethod.GET)
	public String edit(final Model model,
			@PathVariable("sliderId") int sliderId) {
		// Lấy danh sách user
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Slider slider = sliderService.getById(sliderId);
		slider.setUpdateDate(new Date());
		
		model.addAttribute("slider", slider);
		
		return "backend/slider-edit";
	}
	
	//----------- save edit product -----------
	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String productEditSave(final Model model,
			@ModelAttribute("slider") Slider slider,
			@RequestParam("imageSlider") MultipartFile imageSlider) throws IOException {
		
		// Lay danh sach slider tu tbl_slider trong database
		sliderService.saveEditSlider(slider, imageSlider);
		
		return "redirect:/admin/slider/list";
	}
	
	// delete product (inactive)
	@RequestMapping(value = "delete/{sliderId}", method = RequestMethod.GET)
	public String delete(final Model model,
			@PathVariable("sliderId") int sliderId) {
		
		// Lay product trong DB
		Slider slider = sliderService.getById(sliderId);
		slider.setStatus(false);
		sliderService.saveOrUpdate(slider);
		
		return "redirect:/admin/slider/list";
	}
}
