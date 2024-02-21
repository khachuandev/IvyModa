package vn.devpro.FinalProject.controller.frontend;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
import vn.devpro.FinalProject.dto.CartItem;
import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.model.Product;
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
	public ResponseEntity<Map<String, Object>> ajax_addToCart(final Model model, final HttpServletRequest request,
			@RequestBody CartItem cartItem) throws IOException {

		// để lấy session sử dụng thông qua request
		// session tương tự như kiểu Map và được lưu trên main memory.
		HttpSession session = request.getSession();

		// Lấy thông tin giỏ hàng. Đầu tiên giả sử giỏ hàng là null(chưa có giỏ hàng)
		Cart cart = null;

		// kiểm tra xem session có tồn tại đối tượng nào tên là "cart"
		if (session.getAttribute("cart") != null) { // tồn tại 1 giỏ hàng trên session
			cart = (Cart) session.getAttribute("cart");
		} else {// chưa có giỏ hàng nào trên session
			cart = new Cart(); // khởi tạo giỏ hàng mới
			session.setAttribute("cart", cart);
		}

		// Lấy danh sách sản phẩm đang có trong giỏ hàng
		List<CartItem> cartItems = cart.getCartItems();

		// kiểm tra nếu sản phẩm muốn bổ sung vào giỏ hàng có trong giỏ hàng nếu có thì
		// tăng số lượng
		boolean isExists = false;
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) {
				isExists = true;
				// tăng số lượng sản phẩm lên
				item.setQuantity(item.getQuantity() + cartItem.getQuantity());
			}
		}

		// nếu sản phẩm chưa có trong giỏ hàng thì thực hiện add sản phẩm đó vào giỏ
		// hàng
		if (!isExists) {
			// trước khi thêm mới thì lấy sản phẩm trong db
			// và thiết lập tên + đơn giá cho cartitem
			Product productInDb = productService.getById(cartItem.getProductId());

			cartItem.setProductName(productInDb.getName());
			cartItem.setPriceUnit(productInDb.getPrice());
			cartItem.setAvatar(productInDb.getAvatar());

			cart.getCartItems().add(cartItem); // thêm mới sản phẩm vào giỏ hàng

		}

		session.setAttribute("totalPrice", cart.getTotalPrice());

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("status", "TC");
        jsonResult.put("totalItems", getTotalItems(request));
        jsonResult.put("totalPrice", getTotalPrice(request));

        session.setAttribute("totalItems", getTotalItems(request));
        session.setAttribute("totalPrice", getTotalPrice(request));
		return ResponseEntity.ok(jsonResult);
	}

	// Hien thi gio hang
	@RequestMapping(value = "/cart-view", method = RequestMethod.GET)
	public String cartView(final Model model, final HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("cart") != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			model.addAttribute("totalCartPrice", cart.getTotalPrice());
			String message = "Co tong cong " + cart.getTotalProducts() + " trong gio hang";
			model.addAttribute("message", message);
		} else {
			String errorMessage = "Khong co san pham nao trong gio hang";
			model.addAttribute("errorMessage", errorMessage);
		}
		return "frontend/cart-view";
	}

	// Them/bot san pham trong gio hang
//	@RequestMapping(value = "update-product-quantity", method = RequestMethod.POST)
//	ResponseEntity<Map<String, Object>> updateProductQuantity(
//			@RequestBody ProductCart productCart,
//			final HttpServletRequest request) throws IOException {
//		
//		Map<String, Object> jsonResult = new HashMap<String, Object>();
//		
//		HttpSession session = request.getSession();
//		if(session.getAttribute("cart") != null) {
//			Cart cart = (Cart) session.getAttribute("cart");
//			// Cap nhat so luong
//			int index = cart.findProductById(productCart.getProductId());
//			BigInteger oldQuantity = cart.getProductCarts().get(index).getQuantity();
//			BigInteger newQuantity = oldQuantity.add(productCart.getQuantity()); // +-1
//			if(newQuantity.intValue() < 1) {
//				newQuantity = BigInteger.ONE;
//			}
//			cart.getProductCarts().get(index).setQuantity(newQuantity);
//			jsonResult.put("productId", productCart.getProductId());
//		}
//		return ResponseEntity.ok(jsonResult);
//	}
	 

//	@RequestMapping(value = "/place-order", method = RequestMethod.POST)
//	ResponseEntity<Map<String, Object>> updateProductQuantity(@RequestBody Customer customer,
//			final HttpServletRequest request) throws IOException {
//
//		Map<String, Object> jsonResult = new HashMap<String, Object>();
//
//		// Kiem tra thong tin customer bat buoc
//		if (customer.getTxtName() == null || customer.getTxtName().length() <= 0) {
//			jsonResult.put("message", "Bạn chưa nhập họ tên");
//		} else if (customer.getTxtMobile() == null || customer.getTxtMobile().length() <= 0) {
//			jsonResult.put("message", "Bạn chưa nhập số điện thoại");
//		} else {
//			HttpSession session = request.getSession();
//			if (session.getAttribute("cart") == null) {
//				jsonResult.put("message", "Bạn chưa có giỏ hàng");
//			} else {
//				Cart cart = (Cart) session.getAttribute("cart");
//				// Lưu các san pham trong gio hang vao DB: tbl_sale_order_product
//				SaleOrder saleOrder = new SaleOrder();
//				for (ProductCart productCart : cart.getProductCarts()) {
//					SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
//					// Lay san pham trong db
//					Product dbProduct = productService.getById(productCart.getProductId());
//					saleOrderProduct.setProduct(dbProduct);
//					saleOrderProduct.setQuantity(productCart.getQuantity().intValue());
//
//					saleOrder.addRelationalSaleOrderProduct(saleOrderProduct);
//				}
//				// Luu don hang vao tbl_sale_order
//				Calendar cal = Calendar.getInstance();
//				String code = cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DAY_OF_MONTH)
//						+ customer.getTxtMobile();
//				saleOrder.setCode(code);
//
//				User user = new User();
//				user.setId(1);
//				saleOrder.setUser(user);
//
//				saleOrder.setCustomerName(customer.getTxtName());
//				saleOrder.setCustomerMobile(customer.getTxtMobile());
//				saleOrder.setCustomerEmail(customer.getTxtEmail());
//				saleOrder.setCustomerAddress(customer.getTxtAddress());
//
//				saleOrderService.saveOrder(saleOrder);
//				jsonResult.put("message", "Bạn đã đặt hàng thành công cảm ơn bạn");
//
//				// Xoa gio hang sau khi da dat hang
//				cart = new Cart();
//				session.setAttribute("cart", cart);
//			}
//		}
//		return ResponseEntity.ok(jsonResult);
//	}
	
	private int getTotalItems(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return 0;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity();
        }

        return total;
    }

    public BigDecimal getTotalPrice(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return BigDecimal.ZERO;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            BigDecimal itemTotal = item.getPriceUnit().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(itemTotal);
        }

        return total;
    }
}
