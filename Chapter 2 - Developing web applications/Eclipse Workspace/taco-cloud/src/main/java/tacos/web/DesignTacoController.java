package tacos.web;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Taco;
import tacos.Ingredient.Type;

/*Lombok provided annotation that creates SLF4J(Simple logging facade for java) logger in the class at the runtime*/
@Slf4j

/* Identify this class as a controller and mark it for component scanning */
@Controller

/*
 * Specifies that this class will handle requests whose path begin with /design
 * i.e in browser localhost:8080/design
 */
@RequestMapping("/design")
public class DesignTacoController {
	
	//indicates that this method adds one or more model attributes. Will always call this method first. 
	@ModelAttribute
	public void addIngredientsToModel(Model model) {

		// creating new objects of Ingredient.java class and storing the objects in a
		// list
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), 
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), 
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE), 
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE), 
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

		// Getting all the values from the Type enum in the ingredient class and storing
		// them in an array.
		Type[] types = Ingredient.Type.values();

		// for each elements in the types array
		for (Type type : types) {
			// add model attribute(wrap, new ingredient object with type wrap) etc which can
			// directly be accessed by thymeleaf
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}

	}
	
	// returns all the objects from ingredients list that has type equal to the type
			// in the argument
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type)) // ingredients.getType = type where getTYpe is automatically managed by Lombok annotation
				.collect(Collectors.toList());// converts stream into a list
	}

	/*
	 * specifies that when an HTTP GET request is received for design, this method
	 * will handle the request.
	 */
	@GetMapping
	public String showDesignForm(Model model) {
		// adding "design' attribute to the model which is a new Taco() object
		model.addAttribute("taco", new Taco());

	
		// This is a thymeleaf view which is displayed in the browser
		return "design";
	}

	// Handles post request for /design i.e when the user enters the submit button
	// after designing the taco
	@PostMapping

	/*
	 * first check if the submitted form has any validation errors. Errors object
	 * will store any error details during the validation check
	 * 
	 * @modelAttribute  indicates the argument should be retrieved from the model. 
	 * When not present, it should be first instantiated and then added to the model
	 *  and once present in the model, the arguments fields should be populated from all 
	 *  request parameters that have matching names.
	 */
	public String processDesign(@Valid Taco design, Errors errors) {

		// if there is an error then just return the 'design' view
		if (errors.hasErrors()) {
			return "design";
		}
		// Save the taco design
		// We'll do this is chapter 3
		log.info("Processing design: " + design);

		// redirects the user to the relative path /orders/current i.e in the browser
		// http:8080/orders/current
		return "redirect:/orders/current";
	}

}
