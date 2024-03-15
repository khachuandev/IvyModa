package vn.devpro.FinalProject.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Cart {
	// Danh sach cac san pham trong gio hang
	private List<ProductCart> productCarts = new ArrayList<ProductCart>();

	// Tinh tong so san pham trong gio hang
	public BigInteger totalCartProduct() {
		BigInteger total = BigInteger.ZERO;
		for (ProductCart productCart : productCarts) {
			total = total.add(productCart.getQuantity());
		}
		return total;
	}

	// Tinh tong so tien khach hang phai tra cho gio hang
	public BigDecimal totalCartPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (ProductCart productCart : productCarts) {
			total = total.add(productCart.totalPrice());
		}
		return total;
	}

	// Tim san pham trong gio hang theo id
	public int findProductById(int id) {
		for (int index = 0; index < productCarts.size(); index++) {
			if (productCarts.get(index).getProductId() == id) {
				return index;
			}
		}
		return -1;
	}

	public Cart() {
		super();
	}

	public Cart(List<ProductCart> productCarts) {
		super();
		this.productCarts = productCarts;
	}

	public List<ProductCart> getProductCarts() {
		return productCarts;
	}

	public void setProductCarts(List<ProductCart> productCarts) {
		this.productCarts = productCarts;
	}
}
