package vn.devpro.FinalProject.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class Role extends BaseModel {
	
	@Column(name = "name", length = 200, nullable = false)
	private String name;
	
	@Column(name = "description", length = 300, nullable = true)
	private String description;
	
	/*---Mapping many-to-many: tbl_role-to-tbl_user: nhiều vai trò được chia cho nhiều người dùng*/
	// First side: owner (user has some roles)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "role_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users = new ArrayList<User>();
	
	/*---Mapping many-to-one: tbl_role-to-tbl_user: nhiều vai trò được tạo bởi 1 người dùng*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateRole;
	
	/*---Mapping many-to-one: tbl_role-to-tbl_user: nhiều vai trò được cập nhật bởi 1 người dùng*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateRole;
	
	public Role() {
		super();
	}

	public Role(Integer id, Date createDate, Date updateDate, Boolean status, String name, String description,
			List<User> users, User userCreateRole, User userUpdateRole) {
		super(id, createDate, updateDate, status);
		this.name = name;
		this.description = description;
		this.users = users;
		this.userCreateRole = userCreateRole;
		this.userUpdateRole = userUpdateRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUserCreateRole() {
		return userCreateRole;
	}

	public void setUserCreateRole(User userCreateRole) {
		this.userCreateRole = userCreateRole;
	}

	public User getUserUpdateRole() {
		return userUpdateRole;
	}

	public void setUserUpdateRole(User userUpdateRole) {
		this.userUpdateRole = userUpdateRole;
	}
}
