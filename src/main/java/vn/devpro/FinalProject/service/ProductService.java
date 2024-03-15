package vn.devpro.FinalProject.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.dto.SearchModel;
import vn.devpro.FinalProject.model.Product;
import vn.devpro.FinalProject.model.ProductImage;

@Service
public class ProductService extends BaseService<Product> implements FinalProjectConstant {

	@Override
	public Class<Product> clazz() {
		return Product.class;
	}
	
	public List<Product> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_product WHERE status = 1");
	}
	
	// Phuong thuc kiem tra (1) file co duoc upload khong?
	public boolean isUploadFile(MultipartFile file) {
		if(file == null || file.getOriginalFilename().isEmpty()) {
			return false; // khong upload
		}
		return true; // Co upload
	}
	
	// Phuong thuc kiem tra danh sach file co upload file nao khong
	public boolean isUploadFiles(MultipartFile[] files) {
		if(files == null || files.length == 0) {
			return false;
		}
		return true; // co it nhat 1 file
	}
	
	// Luu new product vao database
	@Transactional 
	public Product saveAddProduct(Product product, MultipartFile avatarFile,
			MultipartFile[] imageFiles) throws IOException {
		
		//Luu avatar file
		if(isUploadFile(avatarFile)) {  //co upload file avtaar
			String path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
			File file = new File(path);
			avatarFile.transferTo(file);
			//Luu duong dan vao bang product
			product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
		}
		
		//Luu file images
		if(isUploadFiles(imageFiles)) { //co upload
			//Duyet danh sach anh images
			for(MultipartFile imageFile : imageFiles) {
				if(isUploadFile(imageFile)) { //co upload
					
					//Luu file vao thu muc Product/Image/
					String path = FOLDER_UPLOAD + "Product/Image/" + imageFile.getOriginalFilename();
					File file = new File(path);
					imageFile.transferTo(file);
					
					//Luu duong dan vao bang product_image
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/" + imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					
					//Luu duong dan anh sang bang product_image
					product.addRelationalProductImage(productImage);
				}
			}
		}
		return super.saveOrUpdate(product);
	}	
	
	//---------------Save edit product--------------------
	@Transactional 
	public Product saveEditProduct(Product product, MultipartFile avatarFile,
			MultipartFile[] imageFiles) throws IOException {
		
		// lay product trong db
		Product dbProduct = super.getById(product.getId());
		
		//Luu avatar file moi neu nguoi dung co upload avatar
		if(isUploadFile(avatarFile)) {  //co upload file avatar
			// Xóa avatar cu (Xoa file avatar)
			String path = FOLDER_UPLOAD + "Product/Avatar/" + dbProduct.getAvatar(); // lay duong dan cu
			File file = new File(path);
			file.delete();
			
			// Lưu file avatar moi vao thu muc product/avatar
			path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
			file = new File(path);
			avatarFile.transferTo(file);
			//Luu duong dan vao bang product
			product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
		} 
		else { // Nguoi dung ko upload avatar file
			// Giu nguyen avatar cu
			product.setAvatar(dbProduct.getAvatar());
		}
		
		//Luu file images
		if(isUploadFiles(imageFiles)) { //co upload danh sach anh
			//Duyet danh sach file images
			for(MultipartFile imageFile : imageFiles) {
				if(isUploadFile(imageFile)) { //co upload
					
					//Luu file vao thu muc Product/Image/
					String path = FOLDER_UPLOAD + "Product/Image/" + imageFile.getOriginalFilename();
					File file = new File(path);
					imageFile.transferTo(file);
					
					//Luu duong dan vao bang product_image
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/" + imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					
					//Luu duong dan anh sang bang product_image
					product.addRelationalProductImage(productImage);
				}
			}
		}
		return super.saveOrUpdate(product);
	}
	
	// -----------Delete Product (xóa vĩnh viễn ko nên dùng)----------
	@Autowired
	private ProductImageService productImageService;
	
	@Transactional
	public void deleteProduct(Product product) {
		// lay danh sach anh cua product trong tbl_product_image
		String sql = "SELECT * FROM tbl_product_image WHERE product_id=" + product.getId();
		List<ProductImage> productImages = productImageService.executeNativeSql(sql);
		
		// Xoa lan luot cac anh cua product trong Product/Image va
		// Xoa lan luot cac duong dan anh trong tbl_product_image
		for(ProductImage productImage : productImages) {
			// Xoa file trong thu muc Product image (truoc)
			String path = FOLDER_UPLOAD + productImage.getPath();
			File file = new File(path);
			file.delete();
			
			// Xoa ban ghi thong tin anh trong tbl_product_image
			// product.removeRelationalProductImage(productImage); // restrict 	
		}
		
		// Xoa file avatar trong thu muc Product/Avatar
		String path = FOLDER_UPLOAD + product.getAvatar();
		File file = new File(path);
		file.delete();
		
		// Xoa product trong db
		super.delete(product);
	}
	
	// Search Product
	public List<Product> searchProduct(SearchModel productSearch) {
		// Tao cau lenh sql
		String sql = "SELECT * FROM tbl_product p WHERE 1=1";
		
		// Tim kiem voi tieu chi status 
		if(productSearch.getStatus() != 2) { // Co chon Active/Inactive
			sql += " AND p.status=" + productSearch.getStatus();
		}
		
		// Tim kiem voi tieu chi category
		if(productSearch.getCategoryId() != 0) {
			sql += " AND p.category_id=" + productSearch.getCategoryId();
		}
		
		// Tim kiem voi tieu chi keyword
		if(!StringUtils.isEmpty(productSearch.getKeyword())) { // Kiem tra keyword co rong ko
			String keyword = productSearch.getKeyword().toLowerCase(); 
			
			sql += 	" AND (LOWER(p.name) like '%" + keyword + "%'" +
					" OR LOWER(p.short_description) like '%" + keyword + "%'" +
					" OR LOWER(p.seo) like '%" + keyword + "%')";
		} 
		
		// Tim kiem tu ngay den ngay
		if(!StringUtils.isEmpty(productSearch.getBeginDate()) &&
				!StringUtils.isEmpty(productSearch.getEndDate())) {
			
			String beginDate = productSearch.getBeginDate();
			String endDate = productSearch.getEndDate();
			
			sql += " AND p.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		return super.executeNativeSql(sql);
	}
}
