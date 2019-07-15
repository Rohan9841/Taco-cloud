package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import tacos.Ingredient;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {
	
	public IngredientResourceAssembler() {
		super(RecentTacosController.class, IngredientResource.class);
	}
	
	@Override
	public IngredientResource instantiateResource(Ingredient ingredient) {
		return new IngredientResource(ingredient);
	}
	
	@Override
	public IngredientResource toResource(Ingredient ingredient) {
		return createResourceWithId(ingredient.getId(),ingredient);
	}
}
