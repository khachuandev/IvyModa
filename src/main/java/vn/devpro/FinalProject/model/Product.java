package vn.devpro.FinalProject.model;

import java.math.BigDecimal;
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
@Table(name = "tbl_product")
public class Product extends BaseModel {
	@Column(name = "name", length = 300, nullable = false)
	private String name;
	
	@Column(name = "avatar", length = 300, nullable = true)
	private String avatar;
	
	@Column(name = "price", nullable = true)
	private BigDecimal price;
	
	@Column(name = "sale_price", nullable = true)
	private BigDecimal salePrice;
	
	@Column(name = "gender", length = 10, nullable = true)
	private String gender;
	
	@Column(name = "is_hot", nullable = true)
	private Boolean isHot = Boolean.FALSE;
	
	@Column(name = "short_description", length = 500, nullable = true)
	private String shortDescription;
	
	@Column(name = "detail_description", nullable = true)
	private String detailDescription;
	
	@Column(name = "seo", length = 1000, nullable = true)
	private String seo;
	
	/*---Mapping many-to-one: tbl_product-to-tbl_category: nhiều sp trong 1 danh mục*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	
	/*---Mapping many-to-one: tbl_product-to_tbl_user: nhiều sp được tạo bởi 1 user*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateProduct;
	
	/*---Mapping many-to-one: tbl_product-to_tbl_user: nhiều sp được cập nhật bởi 1 user*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateProduct;
	
	/*---Mapping one-to-many: tbl_product-to-tbl_product_image: 1 sp có nhiều image*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	private Set<ProductImage> productImages = new HashSet<ProductImage>();
	
	// Methods add and remove elements in relational productImage list
	public void addRelationalProductImage (ProductImage productImage) {
		productImages.add(productImage);
		productImage.setProduct(this);
	}
	
	public void removeRelationalProductImage(ProductImage productImage) {
		productImages.remove(productImage);
		productImage.setProduct(this);
	}
	
	/*---Mapping one-to-many: tbl_product-to-tbl_sale_order_product: 1 sp có trong nhiều đơn hàng*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	private Set<SaleOrderProduct> saleOrderProducts = new HashSet<SaleOrderProduct>();
	
	// Methods add and remove elements in relational saleOrderProduct list
	public void addRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		saleOrderProducts.add(saleOrderProduct);
		saleOrderProduct.setProduct(this);
	}
	
	public void removeRelationalSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		saleOrderProducts.remove(saleOrderProduct);
		saleOrderProduct.setProduct(this);
	}
	
	public Product() {
		super();
	}

	public Product(String name, String avatar, BigDecimal price, BigDecimal salePrice, String gender, Boolean isHot,
			String shortDescription, String detailDescription, String seo, Category category, User userCreateProduct,
			User userUpdateProduct, Set<ProductImage> productImages, Set<SaleOrderProduct> saleOrderProducts) {
		super();
		this.name = name;
		this.avatar = avatar;
		this.price = price;
		this.salePrice = salePrice;
		this.gender = gender;
		this.isHot = isHot;
		this.shortDescription = shortDescription;
		this.detailDescription = detailDescription;
		this.seo = seo;
		this.category = category;
		this.userCreateProduct = userCreateProduct;
		this.userUpdateProduct = userUpdateProduct;
		this.productImages = productImages;
		this.saleOrderProducts = saleOrderProducts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUserCreateProduct() {
		return userCreateProduct;
	}

	public void setUserCreateProduct(User userCreateProduct) {
		this.userCreateProduct = userCreateProduct;
	}

	public User getUserUpdateProduct() {
		return userUpdateProduct;
	}

	public void setUserUpdateProduct(User userUpdateProduct) {
		this.userUpdateProduct = userUpdateProduct;
	}

	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public Set<SaleOrderProduct> getSaleOrderProducts() {
		return saleOrderProducts;
	}

	public void setSaleOrderProducts(Set<SaleOrderProduct> saleOrderProducts) {
		this.saleOrderProducts = saleOrderProducts;
	}
}
