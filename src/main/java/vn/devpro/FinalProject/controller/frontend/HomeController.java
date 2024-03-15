package vn.devpro.FinalProject.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.controller.BaseController;
import vn.devpro.FinalProject.model.Product;
import vn.devpro.FinalProject.service.ProductService;
import vn.devpro.FinalProject.service.SliderService;
import vn.devpro.FinalProject.model.ProductImage;
import vn.devpro.FinalProject.model.Slider;
import vn.devpro.FinalProject.service.ProductImageService;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private SliderService sliderService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		
		List<Product> products = productService.findAllActive();
		model.addAttribute("products", products);
		
		List<Slider> sliders = sliderService.findAllActive();
		model.addAttribute("sliders", sliders);
		
		return "frontend/index";
	}
	
	@RequestMapping(value = "/product-detail/{productId}", method = RequestMethod.GET)
	// RequestMapping: ánh xạ một action đến một action method trong controller
	public String productDetail(final Model model,
			final HttpServletRequest request,
			final HttpServletResponse response,
			@PathVariable("productId") int productId) throws IOException {
		
		Product product = productService.getById(productId);
		model.addAttribute("product", product);
		
		List<Product> products  = productService.findAllActive();
		model.addAttribute("products", products);
		
		// lay danh sach anh trong tbl_product_image
		List<ProductImage> productImages = productImageService.getProductImageByProductId(productId);
		model.addAttribute("productImages", productImages);
		
		return "frontend/product-detail";
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	// RequestMapping: ánh xạ một action đến một action method trong controller
	public String product(final Model model) throws IOException {
			
		List<Product> products  = productService.findAllActive();
		model.addAttribute("products", products);
		return "frontend/product";
	}
}
