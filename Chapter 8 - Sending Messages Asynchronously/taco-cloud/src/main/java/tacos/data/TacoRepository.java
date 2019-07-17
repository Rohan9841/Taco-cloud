package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.Taco;


/*it’s first parameter is the entity type that the repository must persist and second 
 * parameter is the type of the entity ID property. 
 * PagingAndSortingRepository extends crudRepository*/
public interface TacoRepository extends PagingAndSortingRepository<Taco,Long>{
	
}
