package vn.devpro.FinalProject.controller.backend;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.FinalProject.controller.BaseController;
import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.dto.SearchModel;
import vn.devpro.FinalProject.model.Category;
import vn.devpro.FinalProject.model.Product;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.CategoryService;
import vn.devpro.FinalProject.service.ProductService;
import vn.devpro.FinalProject.service.UserService;

@Controller
@RequestMapping("/admin/product/")
public class ProductController extends BaseController implements FinalProjectConstant {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
//	@RequestMapping(value = "list", method = RequestMethod.GET)
//	public String list(final Model model) {
//		
//		List<Product> products = productService.findAll();
//		model.addAttribute("products", products);
//		return "backend/product-list";
//	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)	
	public String list(final Model model,
			final HttpServletRequest request) {
		
		// Tìm theo status
		SearchModel productSearch = new SearchModel();
		productSearch.setStatus(2); // All
		String status = request.getParameter("status");
		if(!StringUtils.isEmpty(status)) { // Co chon status
			productSearch.setStatus(Integer.parseInt(status));
		}
		
		// Tìm theo category
		productSearch.setCategoryId(0); // All
		String categoryId = request.getParameter("categoryId");
		if(!StringUtils.isEmpty(categoryId)) { // Co chon category
			productSearch.setCategoryId(Integer.parseInt(categoryId));
		}
		
		// Tìm theo keyword
		productSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if(!StringUtils.isEmpty(keyword)) { // Co tra keyword
			productSearch.setKeyword(keyword);
		}
		
		// Kiem tra tieu chi tim kiem tu ngay den ngay
		String beginDate = null;
		String endDate = null;
		if (!StringUtils.isEmpty(request.getParameter("beginDate"))
				&& !StringUtils.isEmpty(request.getParameter("endDate"))) {
			beginDate = request.getParameter("beginDate");
			endDate = request.getParameter("endDate");
		}
		productSearch.setBeginDate(beginDate);
		productSearch.setEndDate(endDate);
		
		// Bat dau phan trang
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))) { // Bam nut chuyen trang
			productSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		} else {
			productSearch.setCurrentPage(1); // lan dau truy cap luon hien thi trang 1
		}
		
		List<Product> allProducts = productService.searchProduct(productSearch);// Tim kiem
		
		List<Product> products = new ArrayList<Product>(); // DS sp can hien thi trang hien tai
		
		// Tong so trang theo tim kiem
		int totalPages = allProducts.size() / SIZE_OF_PAGE;
		if(allProducts.size() % SIZE_OF_PAGE > 0) {
			totalPages++;
		}
		
		// Neu tong so trang < trang hien tai (lai bam tim kiem)
		if(totalPages < productSearch.getCurrentPage()) {
			productSearch.setCurrentPage(1);
		}
		
		// Lay danh sach sp can hien thi trong 1 trang
		int firstIndex = (productSearch.getCurrentPage() - 1) * SIZE_OF_PAGE; // vị trị dau 1 trang
		int index = firstIndex, count = 0;
		while(index < allProducts.size() && count < SIZE_OF_PAGE) {
			products.add(allProducts.get(index));
			index++;
			count++;
		}
		
		// Phan trang
		productSearch.setSizeOfPage(SIZE_OF_PAGE); // So ban ghi tren 1 trang
		productSearch.setTotalItems(allProducts.size()); // Tong so san pham theo tim kiem
			
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
        //List<Product> products = productService.searchProduct(productSearch);
		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);
		
		return "backend/product-list";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(final Model model) {
		// Lấy danh sách user
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		// Lấy danh sách của category
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		Product product = new Product();
		product.setCreateDate(new Date());
		model.addAttribute("product", product);
		
		return "backend/product-add";
	}
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String productAddSave(final Model model,
			@ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException {
		// Lay danh sach product tu tbl_product trong database
		
		productService.saveAddProduct(product, avatarFile, imageFiles);
		return "redirect:/admin/product/list";
	}
	
	@RequestMapping(value = "edit/{productId}", method = RequestMethod.GET)
	public String edit(final Model model,
			@PathVariable("productId") int productId) {
		// Lấy danh sách user
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		// Lấy danh sách của category
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		Product product = productService.getById(productId);
		product.setUpdateDate(new Date());
		
		model.addAttribute("product", product);
		
		return "backend/product-edit";
	}
	
	//----------- save edit product -----------
	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String productEditSave(final Model model,
			@ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException {
		
		// Lay danh sach product tu tbl_product trong database
		productService.saveEditProduct(product, avatarFile, imageFiles);
		
		return "redirect:/admin/product/list";
	}
	
	// delete product (xoa vinh vien)
//	@RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
//	public String delete(final Model model,
//			@PathVariable("productId") int productId) {
//		
//		// Lay product trong DB
//		Product product = productService.getById(productId);
//		productService.deleteProduct(product);
//		
//		return "redirect:/admin/product/list";
//	}
	
	// delete product (inactive)
	@RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
	public String delete(final Model model,
			@PathVariable("productId") int productId) {
		
		// Lay product trong DB
		Product product = productService.getById(productId);
		product.setStatus(false);
		productService.saveOrUpdate(product);
		
		return "redirect:/admin/product/list";
	}
}
