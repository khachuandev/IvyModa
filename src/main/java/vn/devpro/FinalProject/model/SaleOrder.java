package vn.devpro.FinalProject.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sale_order")
public class SaleOrder extends BaseModel {
	
	@Column(name = "code", length = 60, nullable = false)
	private String code;
	
	@Column(name = "total", nullable = true)
	private BigDecimal total;
	
	@Column(name = "customer_name", length = 300, nullable = false)
	private String customerName;
	
	@Column(name = "customer_mobile", length = 120, nullable = true)
	private String customerMobile;
	
	@Column(name = "customer_email", length = 120, nullable = true)
	private String customerEmail;
	
	@Column(name = "customer_address", length = 300, nullable = true)
	private String customerAddress;
	
	/*---Mapping one-to-many: tbl_sale_order-to-tbl_sale_order_product: 1 hóa đơn chứa thông tin của nhiều đơn hàng*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "saleOrder")
	private Set<SaleOrderProduct> saleOrderProducts = new HashSet<SaleOrderProduct>();
	
	// Methods add and remove elements in relational saleOrder list
	public void addRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		saleOrderProducts.add(saleOrderProduct);
		saleOrderProduct.setSaleOrder(this);
	}
	
	public void removeRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		saleOrderProducts.remove(saleOrderProduct);
		saleOrderProduct.setSaleOrder(this);
	}
	
	/*---Mapping many-to-one: tbl_sale_order-to-tbl_user: nhiều hóa đơn có thể chứa thông tin của 1 người dùng*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id") 
	private User user;
	
	/*---Mapping many-to-one: tbl_sale_order-to-tbl_user: nhiều hóa đơn được tạo bởi 1 người dùng*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private SaleOrder userCreateSaleOrder;
	
	/*---Mapping many-to-one: tbl_sale_order-to-tbl_user: nhiều hóa đơn được cập nhật bởi 1 người dùng*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private SaleOrder userUpdateSaleOrder;
	
	public SaleOrder() {
		super();
	}

	public SaleOrder(Integer id, Date createDate, Date updateDate, Boolean status, String code, BigDecimal total,
			String customerName, String customerMobile, String customerEmail, String customerAddress,
			Set<SaleOrderProduct> saleOrderProducts, User user, SaleOrder userCreateSaleOrder,
			SaleOrder userUpdateSaleOrder) {
		super(id, createDate, updateDate, status);
		this.code = code;
		this.total = total;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.saleOrderProducts = saleOrderProducts;
		this.user = user;
		this.userCreateSaleOrder = userCreateSaleOrder;
		this.userUpdateSaleOrder = userUpdateSaleOrder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Set<SaleOrderProduct> getSaleOrderProducts() {
		return saleOrderProducts;
	}

	public void setSaleOrderProducts(Set<SaleOrderProduct> saleOrderProducts) {
		this.saleOrderProducts = saleOrderProducts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SaleOrder getUserCreateSaleOrder() {
		return userCreateSaleOrder;
	}

	public void setUserCreateSaleOrder(SaleOrder userCreateSaleOrder) {
		this.userCreateSaleOrder = userCreateSaleOrder;
	}

	public SaleOrder getUserUpdateSaleOrder() {
		return userUpdateSaleOrder;
	}

	public void setUserUpdateSaleOrder(SaleOrder userUpdateSaleOrder) {
		this.userUpdateSaleOrder = userUpdateSaleOrder;
	}
}
