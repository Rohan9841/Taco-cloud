package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

/*@Repository declares that this class should be automatically discovered by Spring component 
 * scanning and instantiated as a bean in Spring application context
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;
	
	/*
	 * When JdbcIngredientRepository bean is created, spring injects it with
	 * JdbcTemplate via @Autowired annotated constructor. The constructor assigns
	 *  JdbcTemplate to an instance variable that will be used in other methods
	 */
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	/*
	 * This method returns collection of objects. The sql query returns the result
	 * set. The result set is then converted to Ingredient object using mapRowToIngredient()
	 * method.
	 */ 
	public Iterable<Ingredient> findAll() {
		
		return jdbc.query("select id, name, type from Ingredient",
				this::mapRowToIngredient);
	}

	@Override
	/*
	 * This method returns a single Ingredient object. First sql query is run where
	 * ? = id which is provided in the 3rd argument. The result set produced from
	 * the query is then converted to Ingredient object using mapRowToIngredient().
	 */
	public Ingredient findOne(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id = ?",
				this::mapRowToIngredient,id);
	}

	@Override
	/*
	 * This method saves the ingredient to the database with table named Ingredient. The
	 * three ?,?,? are replaced by the 3 parameters which comes after it in update
	 * method
	 */
	public Ingredient save(Ingredient ingredient) {
		jdbc.update(
			"insert into Ingredient(id,name,type) values (?,?,?)",
			ingredient.getId(),
			ingredient.getName(),
			ingredient.getType().toString()
		);
			
		return ingredient;
	}
	
	/*
	 * This method takes result set and the number of rows in the resultset as an
	 * argument and converts that result set into Ingredient object. It creates new
	 * Ingredient object with parameter id, name, and type which it gets from the
	 * resultset and type is converted to Type datatype as defined in the Ingredient class.
	 */
	private Ingredient mapRowToIngredient(ResultSet rs, int RowNum) throws SQLException{
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type"))
				);
	}

}
