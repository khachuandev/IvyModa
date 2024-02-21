package vn.devpro.FinalProject.controller.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.FinalProject.model.BaseModel;
import vn.devpro.FinalProject.model.SaleOrder;
import vn.devpro.FinalProject.service.ProductService;
import vn.devpro.FinalProject.service.SaleOrderService;
import vn.devpro.FinalProject.service.UserService;

@Controller
@RequestMapping("/admin/order/")
public class OrderListController extends BaseModel {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SaleOrderService saleOrderService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(final Model model) {
        List<SaleOrder> saleOrders = saleOrderService.findAll();
        model.addAttribute("saleOrders", saleOrders);
        return "backend/order-list";
    }
}
