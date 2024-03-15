package vn.devpro.FinalProject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.devpro.FinalProject.dto.SearchModel;
import vn.devpro.FinalProject.model.SaleOrder;
import vn.devpro.FinalProject.model.User;

@Service
public class SaleOrderService extends BaseService<SaleOrder> {
	
	@Autowired
	private UserService userService;

	@Override
	public Class<SaleOrder> clazz() {
		return SaleOrder.class;
	}
	
	@Transactional
	public SaleOrder saveOrder(SaleOrder saleOrder) {
		// Kiểm tra và thiết lập giá trị cho trường user trước khi lưu
		if (saleOrder.getUser() == null && saleOrder.getUserCreateSaleOrder() != null) {
			// Lấy thông tin người dùng từ UserService (hoặc từ nguồn dữ liệu phù hợp)
			User user = userService.getById(saleOrder.getUserCreateSaleOrder().getId());
			// Thiết lập giá trị user cho saleOrder
			saleOrder.setUser(user);
		}
		return super.saveOrUpdate(saleOrder);
	}
	
	// Search SaleOrder
	public List<SaleOrder> searchSaleOrder(SearchModel saleOrderSearch) {
		// Tao lenh sql de tim kiem
		String sql = "SELECT * FROM tbl_sale_order so WHERE 1=1";
		
		// Tim kiem theo status
		if(saleOrderSearch.getStatus() != 2) {
			sql += " AND so.status=" + saleOrderSearch.getStatus();
		}
		
		// Tim kiem theo keyword
		if(!StringUtils.isEmpty(saleOrderSearch.getKeyword())) { // Kiem tra xem keyword co rong ko
			String keyword = saleOrderSearch.getKeyword().toLowerCase();
			
			sql += 	" AND (LOWER(so.code) like '%" + keyword + "%'" +
					" OR LOWER(so.customer_name) like '%" + keyword + "%'" +
					" OR LOWER(so.customer_mobile) like '%" + keyword + "%')";
		}
		
		// Tim kiem tu ngay den ngay
		if(!StringUtils.isEmpty(saleOrderSearch.getBeginDate()) &&
				!StringUtils.isEmpty(saleOrderSearch.getEndDate())) {
			
			String beginDate = saleOrderSearch.getBeginDate();
			String endDate = saleOrderSearch.getEndDate();
			
			sql += " AND so.create_date BETWEEN '" + beginDate + "' AND '" + endDate + "'";
		}
		
		return super.executeNativeSql(sql);
	}
} 
