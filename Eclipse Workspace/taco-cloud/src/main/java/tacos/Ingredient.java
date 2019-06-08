package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//generates getters, hashcode(), toString(),constructors, setters, equals()
@Data

//generates constructor with required arguments that has all final properties as arguments
@RequiredArgsConstructor
public class Ingredient {
	private final String id;
	private final String name;
	private final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
