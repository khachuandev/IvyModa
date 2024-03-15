package vn.devpro.FinalProject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.dto.SearchModel;
import vn.devpro.FinalProject.model.Category;

@Service
public class CategoryService extends BaseService<Category>{

	@Override
	public Class<Category> clazz() {
		return Category.class;
	}
	
	public List<Category> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_category WHERE status=1");
	}
	
	@Transactional
	public void deleteCategoryId(int id) {
		super.deleteById(id);
	}
	
	@Transactional
	public void inactiveCategory(Category category) {
		super.saveOrUpdate(category);
	}
	
	// search Category
	public List<Category> searchCategory(SearchModel categorySearch) {
		// Tao cau lenh sql
		String sql = "SELECT * FROM tbl_category p WHERE 1=1";
		return super.executeNativeSql(sql);
	}
}
