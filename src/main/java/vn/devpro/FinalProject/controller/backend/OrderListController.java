package vn.devpro.FinalProject.controller.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.dto.FinalProjectConstant;
import vn.devpro.FinalProject.dto.SearchModel;
import vn.devpro.FinalProject.model.BaseModel;
import vn.devpro.FinalProject.model.SaleOrder;
import vn.devpro.FinalProject.model.User;
import vn.devpro.FinalProject.service.SaleOrderService;
import vn.devpro.FinalProject.service.UserService;

@Controller
@RequestMapping("/admin/order/")
public class OrderListController extends BaseModel implements FinalProjectConstant {	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SaleOrderService saleOrderService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)	
	public String list(final Model model,
			final HttpServletRequest request) {
		
		// Tìm theo status
		SearchModel saleOrderSearch = new SearchModel();
		saleOrderSearch.setStatus(2); // All
		String status = request.getParameter("status");
		if(!StringUtils.isEmpty(status)) { // Co chon status
			saleOrderSearch.setStatus(Integer.parseInt(status));
		}
		
		//Tim theo keyword
		saleOrderSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if(!StringUtils.isEmpty(keyword)) {
			saleOrderSearch.setKeyword(keyword);
		}
		
		//Ktra tieu chi tim kkiem tu ngay den ngay
		String beginDate = null;
		String endDate = null;
		if(!StringUtils.isEmpty(request.getParameter("beginDate")) && 
				!StringUtils.isEmpty(request.getParameter("endDate"))) {
			beginDate = request.getParameter("beginDate");
			endDate = request.getParameter("endDate");
		}
		saleOrderSearch.setBeginDate(beginDate);
		saleOrderSearch.setEndDate(endDate);
		
		//Bat dau phan trang
		if(!StringUtils.isEmpty(request.getParameter("currentPage"))) {
			saleOrderSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		}
		else {
			saleOrderSearch.setCurrentPage(1);
		}
		
		List<SaleOrder> allSaleOrders = saleOrderService.searchSaleOrder(saleOrderSearch);
		
		List<SaleOrder> saleOrders = new ArrayList<SaleOrder>();
		
		//Tong so trang theo tim kiem
		int totalPages = allSaleOrders.size() / SIZE_OF_PAGE;
		if(allSaleOrders.size() % SIZE_OF_PAGE > 0) {
			totalPages ++;
		}
		
		//Neu tong so trang < trang hien tai (Lại bấm tìm kiếm)
		if(totalPages < saleOrderSearch.getCurrentPage()) {
			saleOrderSearch.setCurrentPage(1);
		}
		
		//Lay sanh sach sp can hien thi trong 1 trang
		int firstIndex = (saleOrderSearch.getCurrentPage() - 1) * SIZE_OF_PAGE; //Vi tri dau 1 trang
		int index = firstIndex, count = 0;
		while(index < allSaleOrders.size() && count < SIZE_OF_PAGE) {
			saleOrders.add(allSaleOrders.get(index));
			index++;
			count++;
		}
		

		model.addAttribute("saleOrderSearch", saleOrderSearch);
		
		//Phan trang
		saleOrderSearch.setSizeOfPage(SIZE_OF_PAGE); //so ban ghi tren mot trang
		saleOrderSearch.setTotalItems(allSaleOrders.size()); //tong so san pham tim kiem
			
		model.addAttribute("saleOrders", saleOrders);
		
		// Tính tổng doanh số bán hàng
		BigDecimal totalSales = BigDecimal.ZERO;

		// Duyệt qua danh sách đơn hàng và cộng dồn giá trị total của mỗi đơn
		for (SaleOrder saleOrder : allSaleOrders) {
			BigDecimal orderTotal;

			if (saleOrder.getTotal() == null) {
				orderTotal = BigDecimal.ZERO;
			} else {
				orderTotal = saleOrder.getTotal();
			}

			totalSales = totalSales.add(orderTotal);
		}
		model.addAttribute("totalSales", totalSales);
		
		return "backend/order-list";
	}
	
	// Edit
	@RequestMapping(value = "edit/{saleOrderId}", method = RequestMethod.GET)
	public String edit(final Model model, @PathVariable("saleOrderId") int saleOrderId) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		SaleOrder saleOrder = saleOrderService.getById(saleOrderId);
		model.addAttribute("saleOrder", saleOrder);
		
		return "backend/order-edit";
	}
	
	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
    public String editSave(final Model model, @ModelAttribute("saleOrder") SaleOrder saleOrder) {
		saleOrderService.saveOrder(saleOrder);
        return "redirect:/admin/order/list";
    }
	
	// delete salerOrder (inactive)
	@RequestMapping(value = "delete/{saleOrderId}", method = RequestMethod.GET)
	public String delete(final Model model,
			@PathVariable("saleOrderId") int saleOrderId) {
		
		// Lay saleOrder trong DB
		SaleOrder saleOrder = saleOrderService.getById(saleOrderId);
		saleOrder.setStatus(false);
		saleOrderService.saveOrUpdate(saleOrder);
		
		return "redirect:/admin/order/list";
	}
}
