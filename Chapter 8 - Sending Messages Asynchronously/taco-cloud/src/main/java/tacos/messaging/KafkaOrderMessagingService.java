package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import tacos.Order;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

	private KafkaTemplate<String,Order> kafkaTemplate;
	
	@Autowired
	public KafkaOrderMessagingService(KafkaTemplate<String,Order> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	/* sends order to the topic named 'tacocloud.orders.topic' */
	@Override
	public void sendOrder(Order order) {
		// TODO Auto-generated method stub
		kafkaTemplate.send("tacocloud.orders.topic",order);

	}

}
