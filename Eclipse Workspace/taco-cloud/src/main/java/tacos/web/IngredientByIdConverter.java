package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

/*This class converts the string of ingredients that the user selects and converts them into the Ingredient Type*/ 
@Component
public class IngredientByIdConverter implements Converter<String,Ingredient>{
	
	private IngredientRepository ingredientRepo;
	
	/* Injects JdbcIngredientRepository into the ingredientRepo variable and uses it to set instance variable*/
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	/*
	 * Converts the id which is the value of the ingredient that is selected by user
	 * into the Ingredient type
	 */
	@Override
	public Ingredient convert(String id) {
		return ingredientRepo.findOne(id);
	}
}
