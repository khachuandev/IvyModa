package vn.devpro.FinalProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_contact")
public class Contact extends BaseModel {
	
	@Column(name = "firstname", length = 120, nullable = false)
	private String firstname;
	
	@Column(name = "lastname", length = 120, nullable = false)
	private String lastname;
	
	@Column(name = "mobile", length = 60, nullable = true)
	private String mobile;
	
	@Column(name = "email", length = 200, nullable = true)
	private String email;
	
	@Column(name = "address", length = 300, nullable = true)
	private String address;
	
	@Column(name = "request_type", length = 300, nullable = true)
	private String requestType;
	
	@Column(name = "message", length = 1200, nullable = true)
	private String message;
	
	public Contact() {
		super();
	}

	public Contact(String firstname, String lastname, String mobile, String email, String address, String requestType,
			String message) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.requestType = requestType;
		this.message = message;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
