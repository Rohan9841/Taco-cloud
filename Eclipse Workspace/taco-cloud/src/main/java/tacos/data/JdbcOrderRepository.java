package tacos.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.Order;
import tacos.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;

	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		
		/*
		 * linked with Taco_Order table and each row in the table will have
		 * autogenerated id
		 */
		this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");

		/* linked to Tao_Order_Tacos table */
		this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");

		/* objectmapper for converting between different datatypes */
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Order save(Order order) {
		
		/*
		 * set the placedAt's value to new Date()
		 */		order.setPlacedAt(new Date());
		 
		/* save the orderId which is returned by saveOrderDetails() method */
		long orderId = saveOrderDetails(order);
		
		/* Then set the Order objects id field to orderId */
		order.setId(orderId);
		
		/* gets all the tacos associated to this order */
		List<Taco> tacos = order.getTacos();
		
		/* for each taco save the taco and orderId into the Taco_Order_Tacos table */
		for(Taco taco: tacos) {
			saveTacoToOrder(taco,orderId);
		}
		
		/* returns the Order object */
		return order;
	}

	/* Method to return the orderId and fill Taco_Order table */
	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		
		/* converts the Order type into Map type and store in variable named 'values' */
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		
		/*
		 * Then execute put method of Map where key = placedAt and value =
		 * order.getPlacedAt() which is set in save method above
		 */
		values.put("placedAt", order.getPlacedAt());
		System.out.println(values);
		/*
		 * inserts the map into Taco_Order table where key = column in table and value =
		 * the actual values in that column. Returns the id which is autogenerated and
		 * convert into long value which is the returned by the method
		 */
		long orderId = orderInserter.executeAndReturnKey(values).longValue();
		
		return orderId;
	}
	
	/* method to fill Taco_Order_Tacos table */
	private void saveTacoToOrder(Taco taco, long orderId) {
		Map<String, Object> values = new HashMap<>();
		
		/*
		 * tacooOrder and taco are the actual columns of Taco_ORder_Tacos table whose
		 * value is set to taco.getId() and orderId respectively
		 */
		values.put("tacoOrder", orderId);
		values.put("taco", taco.getId());
		
		/* inserts the (key,value) pair from values map into the table */
		orderTacoInserter.execute(values);
	}

}
