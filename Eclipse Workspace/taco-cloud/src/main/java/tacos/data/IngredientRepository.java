package tacos.data;

import tacos.Ingredient;

public interface IngredientRepository {
	
	//method to find all the ingredients
	Iterable<Ingredient> findAll();
	
	//method to search only specific ingredient by providing it's id as an argument
	Ingredient findOne(String id);
	
	//method to save the ingredient to the database
	Ingredient save(Ingredient ingredient);
}
