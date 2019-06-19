package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Taco;


/*it’s first parameter is the entity type that the repository must persist and second parameter is the type of the entity ID property. */
public interface TacoRepository extends CrudRepository<Taco,Long>{
	
}
