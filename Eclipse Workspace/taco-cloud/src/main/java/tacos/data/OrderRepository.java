package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Order;

/*it’s first parameter is the entity type that the repository must persist and second parameter is the type of the entity ID property. */
public interface OrderRepository extends CrudRepository<Order,Long>{
	
}
