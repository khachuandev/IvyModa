package vn.devpro.FinalProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.model.ProductImage;

@Service
public class ProductImageService extends BaseService<ProductImage> {
	@Override
	public Class<ProductImage> clazz(){
		return ProductImage.class;
	}
	
	public List<ProductImage> getProductImageByProductId(int productId) {
		String sql = "SELECT * FROM tbl_product_image WHERE product_id=" + productId;
		return super.executeNativeSql(sql);
	}
}
