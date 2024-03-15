package vn.devpro.FinalProject.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.model.Slider;

@Service
public class SliderService extends BaseService<Slider> implements FinalProjectConstant {
	@Override
	public Class<Slider> clazz() {
		return Slider.class;
	}
	
	public List<Slider> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_slider WHERE status = 1");
	}
	
	public List<Slider> findAllActiveImages() {
		return super.executeNativeSql("SELECT * FROM tbl_slider WHERE status = 1 AND type = 'image'");
	}
	
	public List<Slider> findAllActiveVideos() {
		return super.executeNativeSql("SELECT * FROM tbl_slider WHERE status = 1 AND type = 'video'");
	}
	
	// Kiểm tra slider có đc upload hay ko
	public Boolean isUploadSlider(MultipartFile file) {
		if(file == null || file.getOriginalFilename().isEmpty()) {
			return false; // ko upload file
		} 
		return true;
	}
	
	// Luu slider vao database
	@Transactional
	public Slider saveAddSlider(Slider slider, MultipartFile imageSlider) throws IOException {
		if(isUploadSlider(imageSlider)) {
			String path = FOLDER_UPLOAD + "Slider/Image/" + imageSlider.getOriginalFilename();
			File file = new File(path);
			imageSlider.transferTo(file);
			// Luu duong dan vao tbl_slider
			slider.setSlider("Slider/Image/" + imageSlider.getOriginalFilename());
		}
		return super.saveOrUpdate(slider);
	}
	
	// Save edit slider
	@Transactional
	public Slider saveEditSlider(Slider slider, MultipartFile imageSlider) throws IOException {
		// Lay slider tu db
		Slider dbSlider = super.getById(slider.getId());
		
		//Luu avatar file moi neu nguoi dung co upload slider
		if(isUploadSlider(imageSlider)){  // co upload file slider
			// Xoa slider cu (Xoa file slider)
			String path = FOLDER_UPLOAD + "Slider/Image/" + dbSlider.getSlider(); // lay duong dan cu
			File file = new File(path);
			file.delete();
			
			// Luu file slider vao tbl_slider
			path = FOLDER_UPLOAD + "Slider/Image/" + imageSlider.getOriginalFilename();
			file = new File(path);
			imageSlider.transferTo(file);
			slider.setSlider("Slider/Image/" + imageSlider.getOriginalFilename());		
		}
		else { // Nguoi dung upload file slider
			// Giu nguyen slider cu
			slider.setSlider(dbSlider.getSlider());
		}
		return super.saveOrUpdate(slider);
	}
}
