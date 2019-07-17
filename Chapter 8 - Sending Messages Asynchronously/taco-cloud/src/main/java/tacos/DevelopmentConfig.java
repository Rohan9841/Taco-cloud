package tacos;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

/*Stereotype annotation for bean declaration. Indicates this class as a configuration class*/
@Configuration
public class DevelopmentConfig {

	/*
	 * CommandlineRunner is an interface that we cannot create instance of but can
	 * implement it. We need to override the run method to implement it. The method
	 * will be executed right after the application context is loaded and right
	 * before the Spring Application run method is completed. This time we are using it 
	 * load some tacos  to the database right before the application fires up.
	 */
	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepo, PasswordEncoder encoder,IngredientRepository repo, TacoRepository tacoRepo) { // user repo for ease of
																								// testing with a
																								// built-in user
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				userRepo.save(new User("habuma", encoder.encode("password"), 
			            "Craig Walls", "123 North Street", "Cross Roads", "TX", 
			            "76227", "123-123-1234"));
				
				Taco taco1 = new Taco();
				taco1.setTacoName("Carnivore");
				taco1.setIngredients(Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", "WRAP"),
						new Ingredient("COTO", "Corn Tortilla", "WRAP"),
						new Ingredient("GRBF", "Ground Beef", "PROTEIN"), new Ingredient("CARN", "Carnitas", "PROTEIN"),
						new Ingredient("TMTO", "Diced Tomatoes", "VEGGIES"),
						new Ingredient("LETC", "Lettuce", "VEGGIES"), new Ingredient("CHED", "Cheddar", "CHEESE"),
						new Ingredient("JACK", "Monterrey Jack", "CHEESE"), new Ingredient("SLSA", "Salsa", "SAUCE"),
						new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco1);

				Taco taco2 = new Taco();
				taco2.setTacoName("Bovine Bounty");
				taco2.setIngredients(Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", "WRAP"),
						new Ingredient("COTO", "Corn Tortilla", "WRAP"),
						new Ingredient("GRBF", "Ground Beef", "PROTEIN")));
				tacoRepo.save(taco2);

				Taco taco3 = new Taco();
				taco3.setTacoName("Veg-Out");
				taco3.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco3);
				
				Taco taco4 = new Taco();
				taco4.setTacoName("Veg-Out");
				taco4.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco4);
				
				Taco taco5 = new Taco();
				taco5.setTacoName("Veg-Out");
				taco5.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco5);
				
				Taco taco6 = new Taco();
				taco6.setTacoName("Veg-Out");
				taco6.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco6);
				
				Taco taco7= new Taco();
				taco7.setTacoName("Veg-Out");
				taco7.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco7);
				
				Taco taco8 = new Taco();
				taco8.setTacoName("Veg-Out");
				taco8.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco8);
				
				Taco taco9 = new Taco();
				taco9.setTacoName("Veg-Out");
				taco9.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco9);
				
				Taco taco10 = new Taco();
				taco10.setTacoName("Veg-Out");
				taco10.setIngredients(Arrays.asList(new Ingredient("LETC", "Lettuce", "VEGGIES"),
						new Ingredient("CHED", "Cheddar", "CHEESE"), new Ingredient("JACK", "Monterrey Jack", "CHEESE"),
						new Ingredient("SLSA", "Salsa", "SAUCE"), new Ingredient("SRCR", "Sour Cream", "SAUCE")));
				tacoRepo.save(taco10);
				
				

			}
		};
	}

}