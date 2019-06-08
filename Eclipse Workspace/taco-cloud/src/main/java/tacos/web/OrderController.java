package tacos.web;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

/*creates SLF4J logger object at runtime*/
@Slf4j
@Controller

/* specifies that this class handles requests whose path begins with /orders */
@RequestMapping("/orders")
public class OrderController {

	/*
	 * specifies that orderForm() method will handle get requests for
	 * /orders/current
	 */
	@GetMapping("/current")
	public String orderForm(Model model) {

		// adding "order" attribute to model which is a new Order() object
		model.addAttribute("order", new Order());
		/* orderForm is a viewName */
		return "orderForm";
	}

	/*
	 * specifies that processOrder() method will handle post requests for /orders
	 * which is done in orderForm.html, th:action = "@{/orders}"
	 */
	@PostMapping

	// @Valid will check if there are any validation errors
	//@ModelAttribute("order") indicates that "order" model attribute is populated with the data from the orderForm submission before process order is executed
	public String processOrder(@Valid @ModelAttribute("order") Order order, Errors errors) {

		if (errors.hasErrors()) {
			return "orderForm";
		}
		log.info("Order Submitted: " + order);
		return "redirect:/";
	}
}