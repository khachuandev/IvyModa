package vn.devpro.FinalProject.controller.frontend;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.controller.BaseController;
import vn.devpro.FinalProject.dto.Cart;
import vn.devpro.FinalProject.dto.Customer;
import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.dto.ProductCart;
import vn.devpro.FinalProject.model.Product;
import vn.devpro.FinalProject.model.SaleOrder;
import vn.devpro.FinalProject.model.SaleOrderProduct;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.ProductService;
import vn.devpro.FinalProject.service.SaleOrderService;


@Controller
public class CartController extends BaseController implements FinalProjectConstant {

	@Autowired
	private ProductService productService;

	@Autowired
	private SaleOrderService saleOrderService;

	// Them 1 san pham vao gio hang 
	@RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addToCart(final Model model,
			final HttpServletRequest request,
			@RequestBody ProductCart addProduct) throws IOException {
		
		HttpSession session = request.getSession();
		Cart cart = null;
		
		// lay gio hang trong session 
		// + Kiem tra gio hang da duoc tao trong session chua?
		if(session.getAttribute("cart") != null) { // da co gio hang
			cart = (Cart)session.getAttribute("cart"); // lay gio hang
		}
		else { // Chua tao gio hang
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		// lay san pham trong DB
		Product dbProduct = productService.getById(addProduct.getProductId());
		
		// Kiem tra san pham dat mua co trong gio hang chua?
		int index = cart.findProductById(dbProduct.getId());
		if(index != -1) { // San pham co trong gio hang
			cart.getProductCarts().get(index).setQuantity(
					cart.getProductCarts().get(index).getQuantity().add(addProduct.getQuantity()));
		}
		else {
			addProduct.setProductName(dbProduct.getName());
			addProduct.setAvatar(dbProduct.getAvatar());
			addProduct.setPrice(dbProduct.getPrice());
			
			cart.getProductCarts().add(addProduct); // them sp moi vao gio hang
		}
		
		// Thiet lap lai gio hang trong session
		session.setAttribute("cart", cart);
		
		// Tra ve du lieu cho view 
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("totalCartProducts", cart.totalCartProduct());
		jsonResult.put("message", "Đã thêm sản phẩm " + addProduct.getProductName() + " vào giỏ hàng");
		
		return ResponseEntity.ok(jsonResult);
	}
	
	// Hien thi gio hang
	@RequestMapping(value = "/cart-view", method = RequestMethod.GET) 
	public String cartView(final Model model, final HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			model.addAttribute("totalCartPrice", cart.totalCartPrice());
			String message = "Có tổng cộng " + cart.totalCartProduct() + " sản phẩm trong giỏ hàng";
			model.addAttribute("message", message);
		} else {
			String errorMessage = "Không có sản phẩm nào trong giỏ hàng";
			model.addAttribute("errorMessage", errorMessage);
		}
		return "frontend/cart-view";
	}
	
	// Them/bot san pham trong gio hang
	@RequestMapping(value = "update-product-quantity", method = RequestMethod.POST)
	ResponseEntity<Map<String, Object>> updateProductQuantity(
			@RequestBody ProductCart productCart,
			final HttpServletRequest request) throws IOException {
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			// Cap nhat so luong
			int index = cart.findProductById(productCart.getProductId());
			BigInteger oldQuantity = cart.getProductCarts().get(index).getQuantity();
			BigInteger newQuantity = oldQuantity.add(productCart.getQuantity()); // +-1
			if(newQuantity.intValue() < 1) {
				newQuantity = BigInteger.ONE;
			}
			cart.getProductCarts().get(index).setQuantity(newQuantity);
			jsonResult.put("newQuantity", newQuantity);
		}
		jsonResult.put("productId", productCart.getProductId());
		return ResponseEntity.ok(jsonResult);
	}
	
	// Thanh toan hoa don
	@RequestMapping(value = "/place-order", method = RequestMethod.POST)
	ResponseEntity<Map<String, Object>> updateProductQuantity(
	        @RequestBody Customer customer,
	        final HttpServletRequest request) throws IOException {

	    Map<String, Object> jsonResult = new HashMap<String, Object>();

	    // Kiểm tra thông tin customer bắt buộc
	    if(customer.getTxtName() == null || customer.getTxtName().length() <= 0) {
	        jsonResult.put("message", "Bạn chưa nhập họ tên");
	    } else if(customer.getTxtMobile() == null || customer.getTxtMobile().length() <= 0) {
	        jsonResult.put("message", "Bạn chưa nhập số điện thoại");
	    } else {
	        HttpSession session = request.getSession();
	        if(session.getAttribute("cart") == null) {
	            jsonResult.put("message", "Bạn chưa có giỏ hàng");
	        } else {
	            Cart cart = (Cart) session.getAttribute("cart");
	            // Lưu các sản phẩm trong giỏ hàng vào DB: tbl_sale_order_product
	            SaleOrder saleOrder = new SaleOrder();
	            BigDecimal totalCartPrice = BigDecimal.ZERO;

	            for(ProductCart productCart : cart.getProductCarts()) {
	                SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
	                // Lấy sản phẩm từ db
	                Product dbProduct = productService.getById(productCart.getProductId());
	                saleOrderProduct.setProduct(dbProduct);
	                saleOrderProduct.setQuantity(productCart.getQuantity().intValue());

	                // Tính tổng giá của sản phẩm và cập nhật vào tổng giá của đơn hàng
	                BigDecimal productTotalPrice = dbProduct.getPrice()
	                        .multiply(new BigDecimal(productCart.getQuantity()));
	                totalCartPrice = totalCartPrice.add(productTotalPrice);

	                saleOrder.addRelationalSaleOrderProduct(saleOrderProduct);
	            }

	            // Lưu tổng giá vào đơn hàng
	            saleOrder.setTotal(totalCartPrice);

	            // Lưu đơn hàng vào tbl_sale_order
	            Calendar cal = Calendar.getInstance();
	            String code = cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH)
	                + cal.get(Calendar.DAY_OF_MONTH) + customer.getTxtMobile();
	            saleOrder.setCode(code);

	            User user = new User();
	            user.setId(1);
	            saleOrder.setUser(user);

	            saleOrder.setCustomerName(customer.getTxtName());
	            saleOrder.setCustomerMobile(customer.getTxtMobile());
	            saleOrder.setCustomerEmail(customer.getTxtEmail());
	            saleOrder.setCustomerAddress(customer.getTxtAddress());

	            saleOrderService.saveOrder(saleOrder);
	            jsonResult.put("message", "Bạn đã đặt hàng thành công cảm ơn bạn");

	            // Xóa giỏ hàng sau khi đã đặt hàng
	            cart = new Cart(); 
	            session.setAttribute("cart", cart);
	        }
	    }
	    return ResponseEntity.ok(jsonResult);
	}
}
