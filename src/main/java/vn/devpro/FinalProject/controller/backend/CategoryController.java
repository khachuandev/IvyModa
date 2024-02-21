package vn.devpro.FinalProject.controller.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.controller.BaseController;
import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.dto.SearchModel;
import vn.devpro.FinalProject.model.Category;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.CategoryService;
import vn.devpro.FinalProject.service.ProductService;
import vn.devpro.FinalProject.service.UserService;

@Controller
@RequestMapping("/admin/category/")
public class CategoryController extends BaseController implements FinalProjectConstant {
	
	@Autowired // Lấy dữ liệu của category
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	// Hiển thị danh sách category
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model, final HttpServletRequest request) {
		SearchModel categorySearch = new SearchModel();
		// Bat dau phan trang
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))) { // Bam nut chuyen trang
			categorySearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			categorySearch.setCurrentPage(1); // Lan dau truy cap luon hien thi trang 1
		}
		
		List<Category> allCategories = categoryService.searchCategory(categorySearch); // Tim kiem
		
		List<Category> categoriesList = new ArrayList<Category>(); // DS category hien thi tren 1 trang
		
		// Tong so trang theo tim kiem
		int totalPages = allCategories.size() / SIZE_OF_PAGE;
		if(allCategories.size() % SIZE_OF_PAGE > 0) {
			totalPages++;
		}
		
		// Neu tong so trang < trang hien tai (lai bam tim kiem)
		if(totalPages < categorySearch.getCurrentPage()) {
			categorySearch.setCurrentPage(1);
		}
		
		// Lay danh sach sp can hien thi trong 1 trang
		int firstIndex = (categorySearch.getCurrentPage() - 1) * SIZE_OF_PAGE; // vị trị dau 1 trang
		int index = firstIndex, count = 0;
		while(index < allCategories.size() && count < SIZE_OF_PAGE) {
			categoriesList.add(allCategories.get(index));
			index++;
			count++;
		}
		
		// Phan trang
		categorySearch.setSizeOfPage(SIZE_OF_PAGE); // So ban ghi tren 1 trang
		categorySearch.setTotalItems(allCategories.size()); // Tong so san pham theo tim kiem
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories); // đẩy dữ liệu sang view và phải khai báo model
		
		model.addAttribute("categorySearch", categorySearch);
		model.addAttribute("categoriesList", categoriesList);
		
		return "backend/category-list";
	}
	
	// Thêm sản phẩm vào category
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		Category category = new Category();
		category.setCreateDate(new Date());
		model.addAttribute("category", category);
		return "backend/category-add";
	}
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(final Model model, 
			@ModelAttribute("category") Category category) { // lấy dữ liệu từ form sang thì ModelAttribute
		
		categoryService.saveOrUpdate(category);
		return "redirect:/admin/category/list";
	}
	
	@RequestMapping(value = "edit/{categoryId}", method = RequestMethod.GET)
	public String edit(final Model model,
			@PathVariable("categoryId") int categoryId) {
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		// Lay category trong db bang id
		Category category = categoryService.getById(categoryId);
		model.addAttribute("category", category);
		
		return "backend/category-edit";
	}
	
	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(final Model model, 
			@ModelAttribute("category") Category category) { 
		
		categoryService.saveOrUpdate(category);
		return "redirect:/admin/category/list";
	}
	
	// Xóa mất vĩnh viễn (ít dùng)
//	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
//	public String delete(final Model model,
//			@PathVariable("categoryId") int categoryId) {
//		
//		categoryService.deleteById(categoryId);
//		return "redirect:/admin/category/list";
//	}
	
	// Xóa bằng cách vô hiệu hóa 
	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
	public String delete(final Model model,
			@PathVariable("categoryId") int categoryId) {
		
		// Lay category trong DB bang id
		Category category = categoryService.getById(categoryId);
		category.setStatus(false);
		categoryService.inactiveCategory(category);
		
		return "redirect:/admin/category/list";
	}
}
