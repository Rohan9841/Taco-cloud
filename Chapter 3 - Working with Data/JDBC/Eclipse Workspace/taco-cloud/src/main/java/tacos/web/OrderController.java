package tacos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.data.OrderRepository;

@Controller

/* specifies that this class handles requests whose path begins with /orders */
@RequestMapping("/orders")

@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	/*
	 * specifies that orderForm() method will handle get requests for
	 * /orders/current
	 */
	@GetMapping("/current")
	public String orderForm(Model model) {
		
		/* orderForm is a viewName */
		return "orderForm";
	}

	/*
	 * specifies that processOrder() method will handle post requests for /orders
	 * which is done in orderForm.html, th:action = "@{/orders}"
	 */
	@PostMapping

	// @Valid will check if there are any validation errors
	/*
	 * this order is submitted from the form with filled input value which is also
	 * the same order in the session. This is received from the request attribute and not model
	 * 
	 * sessionStatus is set to complete because we need to clear the previous order for new order to begin
	 * 
	 * 
	 */
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {

		if (errors.hasErrors()) {
			return "orderForm";
		}
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}