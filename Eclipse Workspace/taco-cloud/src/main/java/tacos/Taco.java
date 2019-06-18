package tacos;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import  lombok.Data;

//automatically generates essential JavaBean methods at runtime
@Data
public class Taco {
	
	private Long id;
	private Date createdAt;
	
	//javax validation annotation to make sure that the name field is not null
	@NotNull
	
	//javax validation annotation to make sure that the name field must have at least 5 characters
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	
	@NotNull
	
	//javax validation annotation to make sure that the user choose at least two ingredients for the taco 
	@Size(min = 2, message = "You must choose at least 2 ingredients")
	private List<Ingredient> ingredients;
}
