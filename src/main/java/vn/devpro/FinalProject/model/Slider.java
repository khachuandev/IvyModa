package vn.devpro.FinalProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_slider")
public class Slider extends BaseModel {
	
	@Column(name = "title", length = 500, nullable = true)
	private String title;
	
	@Column(name = "slider", length = 300, nullable = true)
	private String slider;
	
	@Column(name = "type", length = 300, nullable = false)
	private String type;
	
	@Column(name = "size", length = 300, nullable = true)
	private String size;
	
	/*---Mapping many-to-one: tbl_slider-to-tbl_user: (for create category)*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateSlider;
	
	/*---Mapping many-to-one: tbl_slider-to-tbl_user: (for update category)*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateSlider;
	
	public Slider() {
		super();
	}

	public Slider(Integer id, Date createDate, Date updateDate, Boolean status, String title, String slider,
			String type, String size, User userCreateSlider, User userUpdateSlider) {
		super(id, createDate, updateDate, status);
		this.title = title;
		this.slider = slider;
		this.type = type;
		this.size = size;
		this.userCreateSlider = userCreateSlider;
		this.userUpdateSlider = userUpdateSlider;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlider() {
		return slider;
	}

	public void setSlider(String slider) {
		this.slider = slider;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public User getUserCreateSlider() {
		return userCreateSlider;
	}

	public void setUserCreateSlider(User userCreateSlider) {
		this.userCreateSlider = userCreateSlider;
	}

	public User getUserUpdateSlider() {
		return userUpdateSlider;
	}

	public void setUserUpdateSlider(User userUpdateSlider) {
		this.userUpdateSlider = userUpdateSlider;
	}
}
