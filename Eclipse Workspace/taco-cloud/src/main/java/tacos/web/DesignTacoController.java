package tacos.web;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
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

import tacos.Ingredient;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.Ingredient.Type;
import tacos.Order;

/* Identify this class as a controller and mark it for component scanning */
@Controller

/*
 * Specifies that this class will handle requests whose path begin with /design
 * i.e in browser localhost:8080/design
 */
@RequestMapping("/design")

/*
 * You need order to be present across multiple requests so that you can create
 * multiple tacos and add them to order. This annotation specifies any model
 * objects like the order attribute to be kept in session and available for
 * multiple requests
 */
@SessionAttributes("order")
public class DesignTacoController {

	/*
	 * This is final because ingredients don't change
	 */ private final IngredientRepository ingredientRepo;

	private TacoRepository designRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	/* Ensures that an order object is created in model */
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	

	/* Ensured that the taco objectis created in model */
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	/*
	 * specifies that when an HTTP GET request is received for design, this method
	 * will handle the request.
	 */

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		// creating new objects of Ingredient.java class and storing the objects in a
		// list
		List<Ingredient> ingredients = new ArrayList<>();

		// Getting all the values from the Type enum in the ingredient class and storing
		// them in an array.
		
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();

		// for each elements in the types array
		for (Type type : types) {

			// add model attribute(wrap, new ingredient object with type wrap) etc which can
			// directly be accessed by thymeleaf
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@GetMapping
	public String showDesignForm(Model model) {

		return "design";

	}

	// returns all the objects from ingredients list that has type equal to the type
	// in the argument
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)) // ingredients.getType = type where getTYpe is
																			// automatically managed by Lombok
																			// annotation
				.collect(Collectors.toList());// converts stream into a list
	}

	// Handles post request for /design i.e when the user enters the submit button
	// after designing the taco
	@PostMapping

	/*
	 * first check if the submitted form has any validation errors. Errors object
	 * will store any error details during the validation check
	 * 
	 * @ModelAttribute will get the Order object from the model and indicate that it's value should come 
	 * directly from model and Spring MVC shouldn't attempt to bind request parameters to it. 
	 * 
	 * The design object comes from request parameters which will have name and ingredients set by the users
	 * 
	 */
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {

		// if there is an error then just return the 'design' view
		if (errors.hasErrors()) {

			return "design";
		}
		
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		System.out.println(design);

		// redirects the user to the relative path /orders/current i.e in the browser
		// http:8080/orders/current
		return "redirect:/orders/current";
	}

}
