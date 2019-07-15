package tacos.web.api;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.Getter;
import tacos.Taco;

/*Specifies that when a list of TacoResource objects is used in a Resources object,
 *  it should be named tacos. A single TacoResource object should be named taco*/
@Relation(value = "taco", collectionRelation = "tacos")

/*Extends ResourceSupport to inherit list of Link objects and methods to manage the list of links
 * TacoResource doesn't have id property from Taco because there's no need to expose database specifi ids in the API
 * The resoure's self link will serve as the identifier for the resource from the perspective of an API client.*/
public class TacoResource extends ResourceSupport{
	
	/*
	 * Uses this propertie's toResource method to convert a given Taco object's list
	 * of Ingredient into list of IngredientResource
	 */
	private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();
	
	/* Lets Lombok generate default getter automatically for the properties */
	@Getter
	private final String name;
	
	@Getter 
	private final Date createdAt;
	
	@Getter 
	private final List<IngredientResource> ingredients;
	
	public TacoResource(Taco taco) {
		this.name = taco.getTacoName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
	}

}
