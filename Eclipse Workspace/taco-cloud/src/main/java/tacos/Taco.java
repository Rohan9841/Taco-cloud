package tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.rest.core.annotation.RestResource;

import  lombok.Data;

//automatically generates essential JavaBean methods at runtime
@Data

/* Indicates this class as a JPA entity */
@Entity

/*
 * @RestResource lets you give an entity any relation name and path you want for
 * spring data rest.
 */
@RestResource(rel = "tacos", path = "tacos")
public class Taco {
	
	/*
	 * indicates id as a property that uniquely identifies this entity in the
	 * database
	 */
	@Id
	
	/* indicates that the id property is generated automatically by the database */
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date createdAt;
	
	//javax validation annotation to make sure that the name field is not null
	@NotNull
	
	//javax validation annotation to make sure that the name field must have at least 5 characters
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String tacoName;
	
	
	@NotNull
	/*
	 * declaring relationship between the Taco and Ingredient as ManyToMany because
	 * a taco can have many ingredients and an ingredient can be a part of many
	 * tacos
	 */
	@ManyToMany(targetEntity = Ingredient.class)
	//javax validation annotation to make sure that the user choose at least two ingredients for the taco 
	@Size(min = 2, message = "You must choose at least 2 ingredients")
	private List<Ingredient> ingredients;
	
	/* indicates that this method is executed before the Taco is persisted */
	@PrePersist
	
	/* method to set createdAt property to current date and time */
	void createdAt() {
		this.createdAt = new Date();
	}
}
