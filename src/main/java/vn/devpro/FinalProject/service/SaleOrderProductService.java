package vn.devpro.FinalProject.service;

import org.springframework.stereotype.Service;

import vn.devpro.FinalProject.model.SaleOrderProduct;

@Service
public class SaleOrderProductService extends BaseService<SaleOrderProduct> {
	@Override
	public Class<SaleOrderProduct> clazz(){
		return SaleOrderProduct.class;
	}
}
