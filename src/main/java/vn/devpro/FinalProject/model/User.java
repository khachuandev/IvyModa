package vn.devpro.FinalProject.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tbl_user")
public class User extends BaseModel implements UserDetails {
	
	@Column(name = "username", length = 120, nullable = false)
	private String username;
	
	@Column(name = "password", length = 120, nullable = false)
	private String password;
	
	@Column(name = "name", length = 120, nullable = true)
	private String name;
	
	@Column(name = "mobile", length = 60, nullable = true)
	private String mobile;
	
	@Column(name = "email", length = 200, nullable = true)
	private String email;
	
	@Column(name = "address", length = 300, nullable = true)
	private String address;
	
	@Column(name = "description", length = 500, nullable = true)
	private String description;
	
	/*---Mapping one-to-many: tbl_user-to-tbl_sale_order: 1 người dùng có thể có nhiều hóa đơn*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<SaleOrder> saleOrders = new HashSet<SaleOrder>();
	
	// Methods remove elements in relational saleOrder list
	public void removeRelationalSaleOrder(SaleOrder saleOrder) {
		saleOrders.add(saleOrder);
		saleOrder.setUser(this);
	}
	
	/*---Mapping many-to-many: tbl_user-to-tbl_role: nhiều người dùng có thể có nhiều vai trò*/
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	private List<Role> roles = new ArrayList<Role>();
	
	// Add and remove elements out of relational userRole list
	public void addRelationalUserRole(Role role) {
		role.getUsers().add(this);
		roles.add(role);
	}
	
	public void removeRelationalUserRole(Role role) {
		role.getUsers().remove(this);
		roles.remove(role);
	}
	
	/*---Mapping one-to-many: tbl_user-to-tbl_category: 1 người dùng có thể tạo nhiều danh mục*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userCreateCategory")
	private Set<Category> userCreateCategories = new HashSet<Category>(); 
	
	// Add and remove elements out of relational userCreateCategories list
	public void addRelationalUserCreateCategory(Category category) {
		userCreateCategories.add(category);
		category.setUserCreateCategory(this);
	}
	
	public void removeRelationalUserCreateCategory(Category category) {
		userCreateCategories.remove(category);
		category.setUserCreateCategory(null);
	}
	
	/*---Mapping one-to-many: tbl_user-to-tbl_category: 1 người dùng có thể cập nhật nhiều danh mục*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userUpdateCategory")
	private Set<Category> userUpdateCategories = new HashSet<Category>();
	
	// Add and remove elements out of relational userUpdateCategories list
	public void addRelationalUserUpdateCatogory(Category category) {
		userUpdateCategories.add(category);
		category.setUserUpdateCategory(this);
	}
	
	public void removeRelationalUserUpdateCategory(Category category) {
		userUpdateCategories.remove(category);
		category.setUserUpdateCategory(null);
	}
	
	/*---Mapping one-to-many: tbl_user-to-tbl_product: 1 người dùng có thể tạo nhiều sp*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userCreateProduct")
	private Set<Product> userCreateProducts = new HashSet<Product>();
	
	// Add and remove elements out of relational userCreateProducts list
	public void addRelationalUserCreateProduct(Product product) {
		userCreateProducts.add(product);
		product.setUserCreateProduct(this);
	}
	
	public void removeRelationalUserCreateProduct(Product product) {
		userCreateProducts.remove(product);
		product.setUserCreateProduct(null);
	}
	
	/*---Mapping one-to-many: tbl_user-to-tbl_product: 1 người dùng có thể cập nhật nhiều sp*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userUpdateProduct")
	private Set<Product> userUpdateProducts = new HashSet<Product>();
	
	// Add and remove elements out of relational userCreateProducts list
	public void addRelationalUserUpdateProduct(Product product) {
		userUpdateProducts.add(product);
		product.setUserUpdateProduct(this);
	}
	
	public void removeRelationalUserUpdateProduct(Product product) {
		userUpdateProducts.remove(product);
		product.setUserUpdateProduct(null);
	}
	
	/*---Mapping one-to-many: tbl_user-to-tbl_slider: 1 người dùng có thể cập nhật nhiều slider*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userUpdateSlider")
	private Set<Slider> userUpdateSliders = new HashSet<Slider>();
	
	// Add and remove elements out of relational userCreateProducts list
	public void addRelationalUserUpdateSlider(Slider slider) {
		userUpdateSliders.add(slider);
		slider.setUserUpdateSlider(this);
	}
	
	public void removeRelationalUserUpdateSlider(Slider slider) {
		userUpdateSliders.remove(slider);
		slider.setUserUpdateSlider(null);
	}
	
	/*---Mapping many-to-one: tbl_user-to-tbl_user: (user create user)*/
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "create_by")
	private User userCreateUser;
	
	/*---Mapping many-to-one: tbl_user-to-tbl_user: (user update user)*/
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "update_by")
	private User userUpdateUser;
	
	/*---Mapping one-to-many: tbl_user-to-tbl_user: (for user create user)*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userCreateUser")
	private Set<User> userCreateUsers = new HashSet<User>();
	
	/*---Mapping one-to-many: tbl_user-to-tbl_user: (for user update user)*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userUpdateUser")
	private Set<User> userUpdateUsers = new HashSet<User>();
	
	public User() {
		super();
	}

	public User(Integer id, Date createDate, Date updateDate, Boolean status, String username, String password,
			String name, String mobile, String email, String address, String description, Set<SaleOrder> saleOrders,
			List<Role> roles, Set<Category> userCreateCategories, Set<Category> userUpdateCategories,
			Set<Product> userCreateProducts, Set<Product> userUpdateProducts, Set<Slider> userUpdateSliders,
			User userCreateUser, User userUpdateUser, Set<User> userCreateUsers, Set<User> userUpdateUsers) {
		super(id, createDate, updateDate, status);
		this.username = username;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.description = description;
		this.saleOrders = saleOrders;
		this.roles = roles;
		this.userCreateCategories = userCreateCategories;
		this.userUpdateCategories = userUpdateCategories;
		this.userCreateProducts = userCreateProducts;
		this.userUpdateProducts = userUpdateProducts;
		this.userUpdateSliders = userUpdateSliders;
		this.userCreateUser = userCreateUser;
		this.userUpdateUser = userUpdateUser;
		this.userCreateUsers = userCreateUsers;
		this.userUpdateUsers = userUpdateUsers;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(Set<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Set<Category> getUserCreateCategories() {
		return userCreateCategories;
	}

	public void setUserCreateCategories(Set<Category> userCreateCategories) {
		this.userCreateCategories = userCreateCategories;
	}

	public Set<Category> getUserUpdateCategories() {
		return userUpdateCategories;
	}

	public void setUserUpdateCategories(Set<Category> userUpdateCategories) {
		this.userUpdateCategories = userUpdateCategories;
	}

	public Set<Product> getUserCreateProducts() {
		return userCreateProducts;
	}

	public void setUserCreateProducts(Set<Product> userCreateProducts) {
		this.userCreateProducts = userCreateProducts;
	}

	public Set<Product> getUserUpdateProducts() {
		return userUpdateProducts;
	}

	public void setUserUpdateProducts(Set<Product> userUpdateProducts) {
		this.userUpdateProducts = userUpdateProducts;
	}

	public Set<Slider> getUserUpdateSliders() {
		return userUpdateSliders;
	}

	public void setUserUpdateSliders(Set<Slider> userUpdateSliders) {
		this.userUpdateSliders = userUpdateSliders;
	}

	public User getUserCreateUser() {
		return userCreateUser;
	}

	public void setUserCreateUser(User userCreateUser) {
		this.userCreateUser = userCreateUser;
	}

	public User getUserUpdateUser() {
		return userUpdateUser;
	}

	public void setUserUpdateUser(User userUpdateUser) {
		this.userUpdateUser = userUpdateUser;
	}

	public Set<User> getUserCreateUsers() {
		return userCreateUsers;
	}

	public void setUserCreateUsers(Set<User> userCreateUsers) {
		this.userCreateUsers = userCreateUsers;
	}

	public Set<User> getUserUpdateUsers() {
		return userUpdateUsers;
	}

	public void setUserUpdateUsers(Set<User> userUpdateUsers) {
		this.userUpdateUsers = userUpdateUsers;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
