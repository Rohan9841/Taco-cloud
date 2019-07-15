package tacos.web.api;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import tacos.Ingredient;

public class IngredientResource extends ResourceSupport {
	
	@Getter
	private String name;
	
	@Getter
	private String type;
	
	public IngredientResource(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type= ingredient.getType();
	}
}
