package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;


/*it’s first parameter is the entity type that the repository must persist and second parameter is the type of the entity ID property. */
public interface IngredientRepository extends CrudRepository<Ingredient,String>{
	
}