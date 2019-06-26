package tacos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.Order;
import tacos.User;
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
	public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
		
		if(order.getName() == null) {
			order.setName(user.getFullName());
		}
		
		if(order.getStreet() == null) {
			order.setStreet(user.getStreet());
		}
		
		if(order.getCity() == null) {
			order.setCity(user.getCity());
		}
		
		if(order.getState() == null) {
			order.setState(user.getState());
		}
		
		if(order.getZip() == null) {
			order.setZip(user.getZip());
		}
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
	 * @AunthenticationPrincipal is used to get the auntheticated principal i.e authenticated User object
	 * 
	 */
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		/*
		 * Pre filling the order form from the User object information which is
		 * currently logged in
		 */
		order.setUser(user);
		
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
