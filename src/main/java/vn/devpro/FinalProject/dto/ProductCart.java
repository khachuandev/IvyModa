package vn.devpro.FinalProject.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductCart {
	private int productId;
	private BigInteger quantity;
	private String productName;
	private String avatar;
	private BigDecimal price;
	
	public BigDecimal totalPrice() {
		if(this.price == null || this.quantity == null) {
			return BigDecimal.ZERO;
		}
		return this.price.multiply(new BigDecimal(this.quantity));
	}
	
	public ProductCart() {
		super();
	}
	
	public ProductCart(int productId, BigInteger quantity, String productName, String avatar, BigDecimal price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.productName = productName;
		this.avatar = avatar;
		this.price = price;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public BigInteger getQuantity() {
		return quantity;
	}
	
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
