package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


//generates getters, hashcode(), toString(),constructors, setters, equals()
@Data

/*
 * generates constructor with required arguments that has all final properties
 * as arguments. @Data implicitly adds this constructor but when
 * noArgsConscturtor is used, this constructor gets removed. An
 * explicit @RequiredArgsConstructor ensures that you will have both required
 * and no required argument constructor
 */

@RequiredArgsConstructor


/*
 * JPA requires entities has a no argument constructor, so
 * lomboks @NoArgsConsctructor does that. It must not be usable so you make it
 * private. There are final properties that needs to be set so you set force =
 * true which makes their values to null.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)

/* Declaring this class as a JPA entity */
@Entity
public class Ingredient {
	
	/*
	 * indicates that id property will uniquely identify the entity in the database
	 */
	@Id
	private final String id;
	private final String name;
	private final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
